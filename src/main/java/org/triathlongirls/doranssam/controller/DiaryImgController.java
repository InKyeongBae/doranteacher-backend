package org.triathlongirls.doranssam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.triathlongirls.doranssam.dto.ApiResponse;
import org.triathlongirls.doranssam.dto.DiaryDetailResponseDto;
import org.triathlongirls.doranssam.dto.RecommendImageRequest;
import org.triathlongirls.doranssam.dto.RecommendImageSelect;
import org.triathlongirls.doranssam.exception.DoranssamErrorCode;
import org.triathlongirls.doranssam.exception.DoranssamException;
import org.triathlongirls.doranssam.service.diaries.DiaryImgService;
import org.triathlongirls.doranssam.service.diaries.DiaryService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("images")
public class DiaryImgController {

    private final ObjectMapper objectMapper;
    private final DiaryImgService diaryImgService;
    private final DiaryService diaryService;

    @PatchMapping("/recommend")
    public ApiResponse selectRecommendImage(@Valid @RequestBody RecommendImageRequest recommendImageRequest) {
        if (!diaryService.validateDiary(recommendImageRequest.getDiaryId()))
            throw new DoranssamException("추천 이미지 선택 권한이 없습니다.", DoranssamErrorCode.FORBIDDEN);
        return new ApiResponse<DiaryDetailResponseDto>().ok(List.of(diaryImgService.selectRecommendImage(recommendImageRequest)));
    }

    @GetMapping("/recommend")
    public ApiResponse getRecommendImage(@RequestParam Long diaryId) {
        if (!diaryService.validateDiary(diaryId))
            throw new DoranssamException("추천 이미지 조회 권한이 없습니다.", DoranssamErrorCode.FORBIDDEN);
        return new ApiResponse<RecommendImageSelect>().ok(diaryImgService.findRecommendImages(diaryId));
    }
}
