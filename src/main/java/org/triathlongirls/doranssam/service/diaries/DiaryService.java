package org.triathlongirls.doranssam.service.diaries;

import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.triathlongirls.doranssam.constant.DiaryStatus;
import org.triathlongirls.doranssam.domain.diaries.Comment;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.domain.diaries.DiaryImg;
import org.triathlongirls.doranssam.domain.diaries.Text;
import org.triathlongirls.doranssam.domain.user.User;
import org.triathlongirls.doranssam.dto.*;
import org.triathlongirls.doranssam.exception.EntityNotFoundException;
import org.triathlongirls.doranssam.repository.DiaryRepository;
import org.triathlongirls.doranssam.service.user.UserService;
import org.triathlongirls.doranssam.util.SecurityUtil;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DiaryService {
    private final UserService userService;
    private final DiaryImgService diaryImgService;
    private final DiaryRepository diaryRepository;

    @Transactional
    public DiarySaveResponseDto save(DiarySaveRequestDto requestDto, MultipartFile multipartFile) {
        String username = SecurityUtil.getCurrentUsername();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.: " + username));

        // text 및 comment 생성
        Text text = new Text(
                requestDto.getOriginalText(),
                requestDto.getCorrectText(),
                null,
                false
        );
        if (!requestDto.getIsPrivate()) {
            Comment comment = new Comment();
            comment.setContent(requestDto.getComment());
            text.setComment(comment);
        }
        Diary savedDiary = diaryRepository.save(requestDto.toEntity(user, text));

        // 직접 업로드한 image 파일 S3에 저장
        if (multipartFile != null && !multipartFile.isEmpty() && !savedDiary.getWantToImage()) {
            DiaryImg diaryImg = new DiaryImg();
            diaryImg.setDiary(savedDiary);
            savedDiary.completeUploadingImg();
            diaryImgService.saveDiaryImg(diaryImg, multipartFile, username);
        } else if (savedDiary.getWantToImage()) {
            savedDiary.setImgStatus(DiaryStatus.PROCESSING);
        }
        return DiarySaveResponseDto.of(savedDiary);
    }


    public Diary findById(Long id) {
        Diary entity = diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 일기가 없습니다. id=" + id));
        return entity;
    }

    public DiaryDetailResponseDto findDiary(Long id) {
        Diary entity = findById(id);
        return DiaryDetailResponseDto.of(entity);
    }


    public Object deleteById(Long id) {
        if (!diaryRepository.existsById(id))
            throw new EntityNotFoundException("해당 일기가 없습니다. id=" + id);
        diaryRepository.deleteById(id);
        return true;
    }

    public List<DiaryCatalogDetailDto> findCatalogThisMonth() {
        String username = SecurityUtil.getCurrentUsername();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.: " + username));

        List<Diary> diaryList = diaryRepository.findDiaryThisMonth(user.getId());

        List<DiaryCatalogDetailDto> diaryCatalogDetails = new ArrayList<>();
        for (Diary diary: diaryList) {
            diaryCatalogDetails.add(
                    new DiaryCatalogDetailDto(
                            diary.getId(),
                            diary.getDate(),
                            diary.loadSelectedImgUrl(),
                            diary.getCreated_at(),
                            diary.getUpdated_at(),
                            diary.getImgStatus().toString(),
                            diary.getCommentStatus().toString()
                    )
            );
        }
        return diaryCatalogDetails;
    }

    public List<DiaryDetailResponseDto> findBookByYearMonth(Integer year, Integer month) {
        String username = SecurityUtil.getCurrentUsername();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.: " + username));

        List<Diary> diaryList = diaryRepository.findByYearMonth(year, month, user.getId());

        List<DiaryDetailResponseDto> diaryDetails = new ArrayList<>();
        for (Diary diary: diaryList) {
            diaryDetails.add( DiaryDetailResponseDto.of(diary));
        }
        return diaryDetails;
    }

    public List<DiaryBookCountInterface> countDiaryEachMonth() {
        String username = SecurityUtil.getCurrentUsername();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.: " + username));
        List<DiaryBookCountInterface> results = diaryRepository.countDiaryByYearMonth(user.getId());

        return results;

    }

    public Boolean validateDiary(Long diaryId) {
        String currentUsername = SecurityUtil.getCurrentUsername();
        User user = userService.findByUsername(currentUsername)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.: " + currentUsername));
        Diary diary = diaryRepository.getById(diaryId);

        if(!StringUtils.equals(user.getUsername(), diary.getUser().getUsername()))
            return false;
        return true;
    }

    public DiaryDetailResponseDto modifyOrder(Long diaryId, @Valid DiaryImageRequest diaryImageRequest) {
        Diary diary = findById(diaryId);
        DiaryStatus newImgStatus = diaryImageRequest.getImgStatus();
        if (newImgStatus.equals(DiaryStatus.NEED_ACTION)) {
            diary.needRecommendImgAction();
        } else if (newImgStatus.equals(DiaryStatus.COMPLETE)) {
            Assert.notNull(diaryImageRequest.getImgUrl(), "image Url must Not be null");

            diary.completeUploadingImg();
            String username = SecurityUtil.getCurrentUsername();
            DiaryImg diaryImg = new DiaryImg();
            diaryImg.setDiary(diary);
            diaryImgService.saveDiaryImgUrl(diaryImg, diaryImageRequest.getImgUrl(), username);
        }
        Diary savedDiary = diaryRepository.save(diary);
        return DiaryDetailResponseDto.of(savedDiary);
    }
}
