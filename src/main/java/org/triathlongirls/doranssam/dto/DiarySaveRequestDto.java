package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.constant.DiaryType;
import org.triathlongirls.doranssam.domain.diaries.Text;
import org.triathlongirls.doranssam.domain.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiarySaveRequestDto {
    @NotBlank
    @Size(max=45)
    private String title;

    @NotNull
    private LocalDate date;

    @NotBlank
    @Size(max=45)
    private String weather;

    @NotBlank
    private String keywords;

    @NotBlank
    private String text;

    @NotNull
    private DiaryType diaryType;

    @NotNull
    private Boolean isPrivate;

    @NotNull
    private Boolean wantToCorrect;

    @NotNull
    private Boolean hasImage;

    public Diary toEntity(User user) {
        Text text = Text.builder()
                .originalText(this.text)
                .correctText("correctText") //TODO: text 처리
                .highlightedText("highlightedText")
                .hasSynonym(true)
                .build();

        return Diary.builder()
                .title(title)
                .date(date)
                .weather(weather)
                .keywords(keywords)
                .diaryType(diaryType)
                .text(text)
                .isPrivate(isPrivate)
                .wantToCorrect(wantToCorrect)
                .hasImage(hasImage)
                .user(user)
                .build();
    }
}
