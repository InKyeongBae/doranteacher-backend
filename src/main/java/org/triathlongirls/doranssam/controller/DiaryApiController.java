package org.triathlongirls.doranssam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.triathlongirls.doranssam.dto.*;
import org.triathlongirls.doranssam.service.diaries.DiaryService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diaries")
public class DiaryApiController {

    private final DiaryService diaryService;

    @PostMapping("")
    public ApiResponse<DiarySaveResponseDto> createDiary(@Valid @RequestBody DiarySaveRequestDto requestDto) {
        return new ApiResponse<DiarySaveResponseDto>().ok(List.of(diaryService.save(requestDto)));
    }

    @GetMapping("")
    public ApiResponse<DiaryCatalogDetailDto> findByYearMonth(@RequestParam Integer year, @RequestParam Integer month) {
        List<DiaryCatalogDetailDto> diaryCatalogDetails = diaryService.findByYearMonth(year, month);
        return new ApiResponse<DiaryCatalogDetailDto>().ok(diaryCatalogDetails);
    }

    @GetMapping("/{id}")
    public ApiResponse<DiaryDetailResponseDto> findById(@PathVariable Long id) {
        return new ApiResponse<DiaryDetailResponseDto>().ok(List.of(diaryService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteDiary(@PathVariable Long id) {
        diaryService.deleteById(id);
        return new ApiResponse<>().ok(List.of());
    }
}
