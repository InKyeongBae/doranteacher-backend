package org.triathlongirls.doranssam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.triathlongirls.doranssam.domain.user.Authority;
import org.triathlongirls.doranssam.domain.user.User;
import org.triathlongirls.doranssam.domain.user.WritingStep;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {

    @NotNull
    @Size(min = 2, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 4, max = 100)
    private String password1;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 4, max = 100)
    private String password2;

    @NotNull
    @Size(min = 2, max = 15)
    private String nickname;


    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password1))
                .nickname(nickname)
                .writingStep(WritingStep.Writing)
                .authority(Authority.ROLE_USER)
                .build();
    }
}
