package org.triathlongirls.doranssam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.triathlongirls.doranssam.dto.*;
import org.triathlongirls.doranssam.exception.DoranssamErrorCode;
import org.triathlongirls.doranssam.exception.DoranssamException;
import org.triathlongirls.doranssam.service.diaries.DiaryService;
import org.triathlongirls.doranssam.service.diaries.FlaskService;

import javax.validation.Valid;
import java.io.File;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diaries")
public class DiaryApiController {

    private final ObjectMapper objectMapper;
    private final DiaryService diaryService;
    private final FlaskService flaskService;

    @PostMapping(name="", consumes="multipart/form-data")
    public ApiResponse<DiarySaveResponseDto> createDiary(
            @RequestParam(value = "images", required = false) MultipartFile multipartFile,
            @RequestParam("data") String diaryData) throws JsonProcessingException {
        DiarySaveRequestDto requestDto = objectMapper.readValue(diaryData, DiarySaveRequestDto.class);
        return new ApiResponse<DiarySaveResponseDto>().ok(List.of(diaryService.save(requestDto, multipartFile)));
    }

    @PatchMapping("/recommend/flask")
    public String recommend(@RequestParam Integer diaryId, @RequestParam String text) throws JsonProcessingException {
        System.out.println(diaryId);
        System.out.println("!!!");
        String flaskResponseDto = flaskService.requestToFlask(text);
        return flaskResponseDto;
    }

    @GetMapping("")
    public ApiResponse<DiaryCatalogDetailDto> diaryCatalogList() {
        List<DiaryCatalogDetailDto> diaryCatalogDetails = diaryService.findCatalogThisMonth();
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
        return new ApiResponse<DiaryDetailResponseDto>().ok(List.of(diaryService.findDiary(id)));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteDiary(@PathVariable Long id) {
        if (!diaryService.validateDiary(id))
            throw new DoranssamException("일기 삭제 권한이 없습니다.", DoranssamErrorCode.FORBIDDEN);
        diaryService.deleteById(id);
        return new ApiResponse<>().ok(List.of());
    }

    @PatchMapping("/{diaryId}")
    public ApiResponse<DiaryDetailResponseDto> orderModify(@PathVariable Long diaryId, @RequestBody @Valid DiaryImageRequest diaryImageRequest) {
        if (!diaryService.validateDiary(diaryId))
            throw new DoranssamException("일기 수정 권한이 없습니다.", DoranssamErrorCode.FORBIDDEN);
        DiaryDetailResponseDto diaryDetail = diaryService.modifyOrder(diaryId, diaryImageRequest);
        return new ApiResponse<DiaryDetailResponseDto>().ok(List.of(diaryDetail));
    }
}
