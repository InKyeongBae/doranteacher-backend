package org.triathlongirls.doranssam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.triathlongirls.doranssam.domain.user.Authority;
import org.triathlongirls.doranssam.domain.user.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotNull
    @Size(min = 2, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 2, max = 15)
    private String nickname;

    private short writingStep;

    public User toUser(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
        return User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .writingStep(writingStep)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
