package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.diaries.Text;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TextResponseDto {
    private Long id;
    private String originalText;
    //TODO: 다른 text, comment, 유의어 추가

    public static TextResponseDto of(Text text) {
        return new TextResponseDto(
                text.getId(),
                text.getOriginalText()
        );
    }
}
