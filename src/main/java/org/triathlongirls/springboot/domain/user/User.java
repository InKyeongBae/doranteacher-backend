package org.triathlongirls.springboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.springboot.domain.BaseTimeEntity;
import org.triathlongirls.springboot.domain.diaries.Diaries;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;

    @Column(length =45)
    private String username;

    private String password;

    private String nickname;

    private short writingStep;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Diaries> diaries = new ArrayList<>();

    @Builder
    public User(String username, String password, String nickname, short writingStep) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.writingStep = writingStep;
    }
}
