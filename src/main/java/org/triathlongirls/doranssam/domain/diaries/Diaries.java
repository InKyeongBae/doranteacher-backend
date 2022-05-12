package org.triathlongirls.doranssam.domain.diaries;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.doranssam.domain.BaseTimeEntity;
import org.triathlongirls.doranssam.domain.user.User;

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

    @Column
    private String keywords;

    @Enumerated(EnumType.STRING)
    private DiaryType diaryType;

    private Boolean isPrivate;

    private Boolean wantToCorrect;

    private Boolean hasImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
        user.getDiaries().add(this);
    }

    @Builder
    public Diaries(
            String title,
            LocalDate date,
            String weather,
            String keywords,
            DiaryType diaryType,
            Boolean isPrivate,
            Boolean wantToCorrect,
            Boolean hasImage,
            User user) {
        this.title = title;
        this.date = date;
        this.weather = weather;
        this.keywords = keywords;
        this.diaryType = diaryType;
        this.isPrivate = isPrivate;
        this.wantToCorrect = wantToCorrect;
        this.hasImage = hasImage;
        setUser(user);
    }
}
