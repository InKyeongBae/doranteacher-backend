package org.triathlongirls.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.springboot.domain.diaries.Diaries;

@Getter
@NoArgsConstructor
public class DiariesSaveRequestDto {
    private String title;

    @Builder
    public DiariesSaveRequestDto(String title) {
        this.title = title;
    }

    public Diaries toEntity() {
        return Diaries.builder()
                .title(title)
                .build();
    }
}
