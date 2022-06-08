package org.triathlongirls.doranssam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.triathlongirls.doranssam.dto.ApiResponse;
import org.triathlongirls.doranssam.dto.DiaryTypeRecommendResult;
import org.triathlongirls.doranssam.service.diaries.DiaryTypeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diary-types")
public class DiaryTypeApiController {

    private final DiaryTypeService diaryTypeService;

    @GetMapping("/recommend")
    public ApiResponse<DiaryTypeRecommendResult> diaryTypeRecommend(@RequestParam String keywords) {
        List<DiaryTypeRecommendResult> results = diaryTypeService.recommendTempDiaryType(keywords);
        return new ApiResponse<DiaryTypeRecommendResult>().ok(results);
    }

//    @GetMapping("/questions?")
//    public ApiResponse<DiaryTypeRecommendResult> diaryQuestionList(@RequestParam String type) {
//        List<DiaryTypeRecommendResult> results = diaryTypeService.findDiaryQuestions(type);
//        return new ApiResponse<DiaryTypeRecommendResult>().ok(results);
//    }
}
