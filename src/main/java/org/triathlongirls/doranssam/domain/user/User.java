package org.triathlongirls.doranssam.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.BaseTimeEntity;
import org.triathlongirls.doranssam.domain.diaries.Diaries;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 15, nullable = false)
    private String nickname;

    @Column(name = "writing_step")
    @Enumerated(EnumType.STRING)
    private WritingStep writingStep;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Diaries> diaries = new ArrayList<>();

    @Builder
    public User(String username, String password, String nickname, WritingStep writingStep, Authority authority, boolean enabled) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.writingStep = writingStep;
        this.authority = authority;
        this.enabled = enabled;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setWritingStep(WritingStep writingStep) {
        this.writingStep = writingStep;
    }
}
