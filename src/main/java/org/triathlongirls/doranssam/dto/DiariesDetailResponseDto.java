package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.diaries.Diaries;
import org.triathlongirls.doranssam.domain.diaries.DiaryType;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiariesDetailResponseDto {

    private Long id;
    private String title;
    private LocalDate date;
    private String weather;
    private String keywords;
    private DiaryType diaryType;
    private Boolean isPrivate;
    private Boolean wantToCorrect;
//TODO: text, comment, 유의어 추가

    public static DiariesDetailResponseDto of(Diaries diaries) {
        return new DiariesDetailResponseDto(
                diaries.getId(),
                diaries.getTitle(),
                diaries.getDate(),
                diaries.getWeather(),
                diaries.getKeywords(),
                diaries.getDiaryType(),
                diaries.getIsPrivate(),
                diaries.getWantToCorrect()
        );
    }
}
