package org.triathlongirls.doranssam.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtTokenRequestDto {
    private String accessToken;
    private String refreshToken;
}
