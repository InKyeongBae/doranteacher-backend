package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.diaries.Diary;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiarySaveResponseDto {

    private Long id;
    //private String highlighted_text;

    public static DiarySaveResponseDto of(Diary diary) {
        return new DiarySaveResponseDto(
        diary.getId()
        //this.highlighted_text = diaries.getTitle(),
        //TODO: text entity
        );
    }
}
