package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.domain.diaries.DiaryImg;
import org.triathlongirls.doranssam.dto.DiaryDetailResponseDto;
import org.triathlongirls.doranssam.dto.RecommendImageRequest;
import org.triathlongirls.doranssam.exception.DoranssamErrorCode;
import org.triathlongirls.doranssam.exception.DoranssamException;
import org.triathlongirls.doranssam.repository.DiaryImgRepository;
import org.triathlongirls.doranssam.repository.DiaryRepository;
import org.triathlongirls.doranssam.service.S3UploaderService;
import org.triathlongirls.doranssam.util.SecurityUtil;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DiaryImgService {

    private final DiaryRepository diaryRepository;
    private final DiaryImgRepository diaryImgRepository;
    private final S3UploaderService s3UploaderService;

    @Transactional
    public void saveDiaryImg(DiaryImg diaryImg, MultipartFile multipartFile, String username, Integer x, Integer y) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String savedName = uuid + extension;
            String imgUrl = s3UploaderService.upload(multipartFile, savedName, username);

            diaryImg.updateDiaryImg(originalFilename, savedName, imgUrl, x, y);
            diaryImgRepository.save(diaryImg);
        } catch (IOException e) {
            throw new DoranssamException(DoranssamErrorCode.S3_UPLOAD_FAILED);
        }
    }

    @Transactional
    public DiaryDetailResponseDto selectRecommendImage(RecommendImageRequest recommendImageRequest) {

        Diary diary = diaryRepository.getById(recommendImageRequest.getDiaryId());
        DiaryImg diaryImg = findRecommendImage(recommendImageRequest.getDiaryId());
        diaryImg.selectDiaryImg(
                recommendImageRequest.getXCoordinate(),
                recommendImageRequest.getYCoordinate()
        );
        diary.completeUploadingImg();
        diaryRepository.save(diary);

        return DiaryDetailResponseDto.of(diary);
    }

    public DiaryImg findRecommendImage(Long diaryId) {
        Diary diary = diaryRepository.getById(diaryId);
        DiaryImg diaryImg = diaryImgRepository.getDiaryImgByDiaryAndIsSelected(diary, false)
                .orElseThrow(() -> new DoranssamException("추천 이미지를 찾을 수 없습니다.", DoranssamErrorCode.ENTITY_NOT_FOUND));
        return diaryImg;
    }
}
