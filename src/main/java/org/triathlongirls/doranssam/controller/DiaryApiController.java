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
    public ApiResponse<DiaryCatalogDetailDto> diaryCatalogList(@RequestParam Integer year, @RequestParam Integer month) {
        List<DiaryCatalogDetailDto> diaryCatalogDetails = diaryService.findCatalogByYearMonth(year, month);
        return new ApiResponse<DiaryCatalogDetailDto>().ok(diaryCatalogDetails);
    }

    @GetMapping("/book")
    public ApiResponse<DiaryDetailResponseDto> diaryBook(@RequestParam Integer year, @RequestParam Integer month) {
        List<DiaryDetailResponseDto> diaryDetails = diaryService.findBookByYearMonth(year, month);
        return new ApiResponse<DiaryDetailResponseDto>().ok(diaryDetails);
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
