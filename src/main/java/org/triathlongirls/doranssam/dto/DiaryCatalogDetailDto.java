package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryCatalogDetailDto {

    private Long diaryId;
    private LocalDate diaryDate;
    private String diaryImgUrl;
    private LocalDateTime diaryCreatedAt;
    private LocalDateTime diaryUpdatedAt;
    private String imgStatus;
    private String commentStatus;

}
