package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.diaries.Text;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TextResponseDto {
    private Long text_id;
    private String originalText;
    private String correctText;
    private String highlightedText;
    //private String comment; //TODO: comment

    public static TextResponseDto of(Text text) {
        return new TextResponseDto(
                text.getId(),
                text.getOriginalText(),
                text.getCorrectText(),
                text.getHighlightedText()
                //text.getComment().getContent()
        );
    }
}
