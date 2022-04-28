package org.triathlongirls.doranssam.web.dto;

import lombok.Getter;
import org.triathlongirls.doranssam.domain.user.User;

@Getter
public class UserResponseDto {
    private Long id;
    private String userName;
    private String nickname;
    private short writingStep;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.userName = entity.getUsername();
        this.nickname = entity.getNickname();
        this.writingStep = entity.getWritingStep();
    }
}
