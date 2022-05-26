package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.diaries.Diary;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiarySaveResponseDto {

    private Long diaryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String username;
    private String nickname;

    public static DiarySaveResponseDto of(Diary diary) {
        return new DiarySaveResponseDto(
                diary.getId(),
                diary.getCreated_at(),
                diary.getUpdated_at(),
                diary.getUser().getUsername(),
                diary.getUser().getNickname()
        );
    }
}
