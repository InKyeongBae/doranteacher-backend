package org.triathlongirls.doranssam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.triathlongirls.doranssam.dto.ApiResponse;
import org.triathlongirls.doranssam.dto.DiariesDetailResponseDto;
import org.triathlongirls.doranssam.dto.DiariesSaveResponseDto;
import org.triathlongirls.doranssam.dto.DiariesSaveRequestDto;
import org.triathlongirls.doranssam.service.diaries.DiariesService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diaries")
public class DiariesApiController {

    private final DiariesService diariesService;

    @PostMapping("")
    public ApiResponse<DiariesSaveResponseDto> createDiary(@Valid @RequestBody DiariesSaveRequestDto requestDto) {
        return new ApiResponse<DiariesSaveResponseDto>().ok(List.of(diariesService.save(requestDto)));
    }

    @GetMapping("/{id}")
    public ApiResponse<DiariesDetailResponseDto> findById(@PathVariable Long id) {
        return new ApiResponse<DiariesDetailResponseDto>().ok(List.of(diariesService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteDiary(@PathVariable Long id) {
        diariesService.deleteById(id);
        return new ApiResponse<>().ok(List.of());
    }
}
