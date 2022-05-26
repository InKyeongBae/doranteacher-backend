package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.constant.DiaryType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDetailResponseDto {

    private Long diary_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String title;
    private LocalDate date;
    private String weather;
    private String keywords;
    private DiaryType diaryType;
    private Boolean isPrivate;
    private Boolean wantToCorrect;
    private TextResponseDto text;

    public static DiaryDetailResponseDto of(Diary diary) {
        return new DiaryDetailResponseDto(
                diary.getId(),
                diary.getCreated_at(),
                diary.getUpdated_at(),
                diary.getTitle(),
                diary.getDate(),
                diary.getWeather(),
                diary.getKeywords(),
                diary.getDiaryType(),
                diary.getIsPrivate(),
                diary.getWantToCorrect(),
                TextResponseDto.of(diary.getText())
        );
    }
}
