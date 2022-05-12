package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.diaries.Diaries;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiariesSaveResponseDto {

    private Long id;
    //private String highlighted_text;

    public static DiariesSaveResponseDto of(Diaries diaries) {
        return new DiariesSaveResponseDto(
        diaries.getId()
        //this.highlighted_text = diaries.getTitle(),
        //TODO: text entity
        );
    }
}
