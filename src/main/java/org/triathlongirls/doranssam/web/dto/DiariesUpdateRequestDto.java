package org.triathlongirls.doranssam.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiariesUpdateRequestDto {

    private String title;

    @Builder
    public DiariesUpdateRequestDto(String title) {
        this.title = title;
    }
}
