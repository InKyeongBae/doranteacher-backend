package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.domain.diaries.DiaryImg;
import org.triathlongirls.doranssam.dto.DiaryDetailResponseDto;
import org.triathlongirls.doranssam.dto.RecommendImageRequest;
import org.triathlongirls.doranssam.dto.RecommendImageSelect;
import org.triathlongirls.doranssam.exception.DoranssamErrorCode;
import org.triathlongirls.doranssam.exception.DoranssamException;
import org.triathlongirls.doranssam.repository.DiaryImgRepository;
import org.triathlongirls.doranssam.repository.DiaryRepository;
import org.triathlongirls.doranssam.service.S3UploaderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class DiaryImgService {

    private final DiaryRepository diaryRepository;
    private final DiaryImgRepository diaryImgRepository;
    private final S3UploaderService s3UploaderService;

    @Transactional
    public void saveDiaryImg(DiaryImg diaryImg, MultipartFile multipartFile, String username) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String savedName = uuid + extension;
            String imgUrl = s3UploaderService.upload(multipartFile, savedName, username);

            diaryImg.updateDiaryImg(originalFilename, savedName, imgUrl, true);
            diaryImgRepository.save(diaryImg);
        } catch (IOException e) {
            throw new DoranssamException(DoranssamErrorCode.S3_UPLOAD_FAILED);
        }
    }

    @Transactional
    public DiaryDetailResponseDto selectRecommendImage(RecommendImageRequest recommendImageRequest) {

        Diary diary = diaryRepository.getById(recommendImageRequest.getDiaryId());
        DiaryImg diaryImg = diaryImgRepository.getById(recommendImageRequest.getSelectedImgId());
        diaryImg.selectDiaryImg();
        diary.completeUploadingImg();
        diaryRepository.save(diary);

        return DiaryDetailResponseDto.of(diary);
    }

    public List<RecommendImageSelect> findRecommendImages(Long diaryId) {
        Diary diary = diaryRepository.getById(diaryId);
        List<DiaryImg> recommendImgs = diaryImgRepository.findDiaryImgsByDiaryAndIsSelected(diary, false);
        if (recommendImgs.isEmpty()) {
            throw new DoranssamException("추천 이미지를 찾을 수 없습니다.", DoranssamErrorCode.ENTITY_NOT_FOUND);
        }

        List<RecommendImageSelect> results = new ArrayList<>();
        for (DiaryImg img : recommendImgs) {
            results.add(new RecommendImageSelect(img.getId(), img.getImgUrl()));
        }
        return results;
    }

    @Async
    @Transactional
    public void generateRecommendImage(Diary diary) {
        try {
            String url = "https://doran-image.s3.ap-northeast-2.amazonaws.com/recommend_";
            String extension = ".png";
            for (int i = 1; i < 9; i++) {
                DiaryImg diaryImg = new DiaryImg();
                diaryImg.setDiary(diary);
                Thread.sleep(TimeUnit.MINUTES.toMillis(3));
                diaryImg.updateDiaryImg("", "", url + i + extension, false);
                diaryImgRepository.save(diaryImg);
            }
            diary.needRecommendImgAction();
            diaryRepository.save(diary);
            log.info("endAsync");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
