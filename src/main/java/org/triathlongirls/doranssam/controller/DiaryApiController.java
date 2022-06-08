package org.triathlongirls.doranssam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.triathlongirls.doranssam.dto.*;
import org.triathlongirls.doranssam.exception.DoranssamErrorCode;
import org.triathlongirls.doranssam.exception.DoranssamException;
import org.triathlongirls.doranssam.service.diaries.DiaryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diaries")
public class DiaryApiController {

    private final ObjectMapper objectMapper;
    private final DiaryService diaryService;

    @PostMapping(name="", consumes="multipart/form-data")
    public ApiResponse<DiarySaveResponseDto> createDiary(
            @RequestParam(value = "images", required = false) MultipartFile multipartFile,
            @RequestParam("data") String diaryData) throws JsonProcessingException {
        DiarySaveRequestDto requestDto = objectMapper.readValue(diaryData, DiarySaveRequestDto.class);
        return new ApiResponse<DiarySaveResponseDto>().ok(List.of(diaryService.save(requestDto, multipartFile)));
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

    @GetMapping("/book/count")
    public ApiResponse<DiaryBookCountInterface> diaryBookCount() {
        List<DiaryBookCountInterface> diaryDetails = diaryService.countDiaryEachMonth();
        return new ApiResponse<DiaryBookCountInterface>().ok(diaryDetails);
    }

    @GetMapping("/{id}")
    public ApiResponse<DiaryDetailResponseDto> findById(@PathVariable Long id) {
        if (!diaryService.validateDiary(id))
            throw new DoranssamException("일기 조회 권한이 없습니다.", DoranssamErrorCode.FORBIDDEN);
        return new ApiResponse<DiaryDetailResponseDto>().ok(List.of(diaryService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteDiary(@PathVariable Long id) {
        if (!diaryService.validateDiary(id))
            throw new DoranssamException("일기 삭제 권한이 없습니다.", DoranssamErrorCode.FORBIDDEN);
        diaryService.deleteById(id);
        return new ApiResponse<>().ok(List.of());
    }
}
