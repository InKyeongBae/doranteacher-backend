package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.user.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String nickname;
    private short writingStep;

    public static UserResponseDto of(User User) {
        return new UserResponseDto(
                User.getId(),
                User.getUsername(),
                User.getNickname(),
                User.getWritingStep()
        );
    }
}