package org.triathlongirls.doranssam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ApiResponse<DiaryTypeRecommendResult> diaryTypeRecommend(@RequestBody String keywords) {
        List<DiaryTypeRecommendResult> results = diaryTypeService.recommendDiaryType(keywords);
        return new ApiResponse<DiaryTypeRecommendResult>().ok(results);
    }
}
