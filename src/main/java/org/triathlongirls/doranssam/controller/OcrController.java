package org.triathlongirls.doranssam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.triathlongirls.doranssam.service.OcrService;
import org.triathlongirls.doranssam.dto.OcrDto;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OcrController {

    private final OcrService ocrService;

    @PostMapping("/ocrtext")
    public OcrDto ocrText(@RequestBody OcrDto ocrDto) {
        return ocrService.detectDocumentText(ocrDto.getFilepath());
    }

}
