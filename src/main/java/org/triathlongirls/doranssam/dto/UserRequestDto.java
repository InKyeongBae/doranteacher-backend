package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String nickname;
    @Max(2) @Min(1)
    private int writingStep;
}