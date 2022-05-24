package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.constant.DiaryType;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDetailResponseDto {

    private Long id;
    private String title;
    private LocalDate date;
    private String weather;
    private String keywords;
    private DiaryType diaryType;
    private Boolean isPrivate;
    private Boolean wantToCorrect;
//TODO: text, comment, 유의어 추가

    public static DiaryDetailResponseDto of(Diary diary) {
        return new DiaryDetailResponseDto(
                diary.getId(),
                diary.getTitle(),
                diary.getDate(),
                diary.getWeather(),
                diary.getKeywords(),
                diary.getDiaryType(),
                diary.getIsPrivate(),
                diary.getWantToCorrect()
        );
    }
}
