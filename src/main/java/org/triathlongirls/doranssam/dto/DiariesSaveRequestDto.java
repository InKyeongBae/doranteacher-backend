package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.diaries.Diaries;
import org.triathlongirls.doranssam.domain.diaries.DiaryType;
import org.triathlongirls.doranssam.domain.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiariesSaveRequestDto {
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

    private DiaryType diaryType;

    private Boolean isPrivate;

    private Boolean wantToCorrect;

    private Boolean hasImage;

    public Diaries toEntity(User user) {
        return Diaries.builder()
                .title(title)
                .date(date)
                .weather(weather)
                .keywords(keywords)
                .diaryType(diaryType)
                .isPrivate(isPrivate)
                .wantToCorrect(wantToCorrect)
                .hasImage(hasImage)
                .user(user)
                .build();
    }
}
