package org.triathlongirls.doranssam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.triathlongirls.doranssam.constant.DiaryStatus;
import org.triathlongirls.doranssam.dto.ApiResponse;
import org.triathlongirls.doranssam.dto.DiaryDetailResponseDto;
import org.triathlongirls.doranssam.exception.DoranssamErrorCode;
import org.triathlongirls.doranssam.exception.DoranssamException;
import org.triathlongirls.doranssam.service.diaries.DiaryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("images")
public class DiaryImgController {
    private final DiaryService diaryService;

    @PostMapping(name = "", consumes = "multipart/form-data")
    public ApiResponse selectRecommendImage(
            @RequestParam(value = "images") MultipartFile multipartFile,
            @RequestParam Long diaryId) {
        if (!diaryService.validateDiary(diaryId))
            throw new DoranssamException("추천 이미지 선택 권한이 없습니다.", DoranssamErrorCode.FORBIDDEN);
        return new ApiResponse<DiaryDetailResponseDto>().ok(List.of(diaryService.selectImage(diaryId, multipartFile)));
    }
}
