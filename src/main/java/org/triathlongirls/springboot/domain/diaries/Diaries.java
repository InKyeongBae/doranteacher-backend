package org.triathlongirls.springboot.domain.diaries;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.springboot.domain.BaseTimeEntity;
import org.triathlongirls.springboot.domain.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Diaries extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @Column(length = 45)
    private String title;

    private LocalDate date;

    @Column(length = 45)
    private String weather;

    @Enumerated(EnumType.STRING)
    private DiaryType diaryType;

    private Boolean is_private;

    private Boolean want_to_correct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
        user.getDiaries().add(this);
    }

    @Builder
    public Diaries(String title, LocalDate date, String weather, DiaryType diaryType, Boolean is_private, Boolean want_to_correct, User user) {
        this.title = title;
        this.date = date;
        this.weather = weather;
        this.diaryType = diaryType;
        this.is_private = is_private;
        this.want_to_correct = want_to_correct;
        setUser(user);
    }
}
