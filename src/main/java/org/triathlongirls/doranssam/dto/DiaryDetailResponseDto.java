package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.constant.DiaryType;
import org.triathlongirls.doranssam.domain.diaries.Diary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDetailResponseDto {

    private Long diaryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String title;
    private LocalDate date;
    private String weather;
    private List<String> keywords;
    private DiaryType diaryType;
    private Boolean isPrivate;
    private Boolean wantToCorrect;
    private Boolean wantToImage;
    private Boolean hasImage;
    private Integer step;
    private String original_text;
    private String correct_text;
    private String selectedImage;
    private String imgStatus;
    private String commentStatus;

    public static DiaryDetailResponseDto of(Diary diary) {
        List<String> keyword_list = List.of(diary.getKeywords().split(","));
        return new DiaryDetailResponseDto(
                diary.getId(),
                diary.getCreated_at(),
                diary.getUpdated_at(),
                diary.getTitle(),
                diary.getDate(),
                diary.getWeather(),
                keyword_list,
                diary.getDiaryType(),
                diary.getIsPrivate(),
                diary.getWantToCorrect(),
                diary.getWantToImage(),
                diary.getHasImage(),
                diary.getWritingStep().getValue(),
                diary.getText().getOriginalText(),
                diary.getText().getCorrectText(),
                diary.loadSelectedImgUrl(),
                diary.getImgStatus().toString(),
                diary.getCommentStatus().toString()
        );
    }
}
