package org.triathlongirls.doranssam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.triathlongirls.doranssam.dto.ApiResponse;
import org.triathlongirls.doranssam.dto.DiaryTypeRecommendResult;
import org.triathlongirls.doranssam.service.diaries.DiaryTypeService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diary-types")
public class DiaryTypeApiController {

    private final DiaryTypeService diaryTypeService;

    @GetMapping("/recommend")
    public ApiResponse<DiaryTypeRecommendResult> diaryTypeRecommend(@RequestParam String keywords) {
        List<DiaryTypeRecommendResult> results = diaryTypeService.recommendDiaryTypeFromWord2VecServer(keywords);
        return new ApiResponse<DiaryTypeRecommendResult>().ok(results);
    }

    @GetMapping("/questions/step2")
    public ApiResponse<String> step2QuestionList(@RequestParam String type) throws UnsupportedEncodingException {
        return new ApiResponse<String>().ok(List.of(diaryTypeService.getStep2Questions(type)));
    }

    @GetMapping("/questions/step1")
    public ApiResponse<String> step1QuestionList(@RequestParam String type) throws UnsupportedEncodingException {
        return new ApiResponse<String>().ok(diaryTypeService.getStep1Questions(type));
    }
}
