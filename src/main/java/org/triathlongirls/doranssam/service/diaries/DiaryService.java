package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.domain.diaries.DiaryImg;
import org.triathlongirls.doranssam.domain.user.User;
import org.triathlongirls.doranssam.dto.DiaryCatalogDetailDto;
import org.triathlongirls.doranssam.dto.DiaryDetailResponseDto;
import org.triathlongirls.doranssam.dto.DiarySaveRequestDto;
import org.triathlongirls.doranssam.dto.DiarySaveResponseDto;
import org.triathlongirls.doranssam.exception.DoranssamErrorCode;
import org.triathlongirls.doranssam.exception.DoranssamException;
import org.triathlongirls.doranssam.exception.EntityNotFoundException;
import org.triathlongirls.doranssam.repository.DiaryRepository;
import org.triathlongirls.doranssam.service.user.UserService;
import org.triathlongirls.doranssam.service.S3UploaderService;
import org.triathlongirls.doranssam.util.SecurityUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        Diary savedDiary = diaryRepository.save(requestDto.toEntity(user));

        // 직접 업로드한 image 파일 S3에 저장
        if (multipartFile != null && !multipartFile.isEmpty() && !savedDiary.getWantToImage()) {
            DiaryImg diaryImg = new DiaryImg();
            diaryImg.setDiary(savedDiary);
            savedDiary.completeUploadingImg();
            diaryImgService.saveDiaryImg(diaryImg, multipartFile, username);
        }

        return DiarySaveResponseDto.of(savedDiary);
    }

/*
    @Transactional
    public Long update(Long id, DiariesUpdateRequestDto requestDto) {
        Diaries diaries = diariesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일기가 없습니다. id=" + id));
        //diaries.update(requestDto.getTitle());

        return id;
    }
*/
    public DiaryDetailResponseDto findById(Long id) {
        Diary entity = diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 일기가 없습니다. id=" + id));

        return DiaryDetailResponseDto.of(entity);
    }


    public Object deleteById(Long id) {
        Diary entity = diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 일기가 없습니다. id=" + id));
        diaryRepository.deleteById(entity.getId());
        return true;
    }

    public List<DiaryCatalogDetailDto> findCatalogByYearMonth(Integer year, Integer month) {
        String username = SecurityUtil.getCurrentUsername();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.: " + username));

        List<Diary> diaryList = diaryRepository.findByYearMonth(year, month, user.getId());

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
}
