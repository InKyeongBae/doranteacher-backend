package org.triathlongirls.springboot.web.dto;

import lombok.Getter;
import org.triathlongirls.springboot.domain.diaries.Diaries;

@Getter
public class DiariesResponseDto {

    private Long id;
    private String title;

    public DiariesResponseDto(Diaries entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
    }
}
