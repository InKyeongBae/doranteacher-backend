package org.triathlongirls.doranssam.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Data
public class FlaskResponseDto {

    private String result;

    public FlaskResponseDto(String result){
        this.result = result;
    }
}
