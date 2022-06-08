package org.triathlongirls.doranssam.domain.diaries;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.triathlongirls.doranssam.constant.DiaryStatus;
import org.triathlongirls.doranssam.constant.DiaryType;
import org.triathlongirls.doranssam.domain.BaseTimeEntity;
import org.triathlongirls.doranssam.domain.user.User;
import org.triathlongirls.doranssam.domain.user.WritingStep;
import org.triathlongirls.doranssam.exception.DoranssamErrorCode;
import org.triathlongirls.doranssam.exception.DoranssamException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "diaries")
public class Diary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;
    @NotNull
    @Column(length = 45)
    private String title;
    @NotNull
    private LocalDate date;
    @NotNull
    @Column(length = 45)
    private String weather;
    @NotNull
    @Column
    private String keywords;
    @NotNull
    @Enumerated(EnumType.STRING)
    private DiaryType diaryType;
    @NotNull
    @Column(name = "is_private")
    private Boolean isPrivate;
    @NotNull
    @Column(name = "want_to_correct")
    private Boolean wantToCorrect;
    @NotNull
    @Column(name = "want_to_image")
    private Boolean wantToImage;

    @Column(name = "has_image")
    private Boolean hasImage;

    @Column(name = "img_status")
    @Enumerated(EnumType.STRING)
    private DiaryStatus imgStatus;

    @Column(name = "comment_status")
    @Enumerated(EnumType.STRING)
    private DiaryStatus commentStatus;

    @NotNull
    @Column(name = "writing_step")
    @Enumerated(EnumType.STRING)
    private WritingStep writingStep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "text_id")
    private Text text;

    @OneToMany(mappedBy = "diary")
    private List<DiaryImg> diaryImgs = new ArrayList<>();

    public void setUser(User user) {
        this.user = user;
        user.getDiaries().add(this);
    }

    public void completeUploadingImg() {
        this.hasImage = true;
        this.imgStatus = DiaryStatus.COMPLETE;
    }

    @Builder
    public Diary(
            String title,
            LocalDate date,
            String weather,
            String keywords,
            DiaryType diaryType,
            Text text,
            Boolean isPrivate,
            Boolean wantToCorrect,
            Boolean wantToImage,
            Boolean hasImage,
            User user,
            DiaryStatus imgStatus,
            DiaryStatus commentStatus) {
        this.title = title;
        this.date = date;
        this.weather = weather;
        this.keywords = keywords;
        this.diaryType = diaryType;
        this.text = text;
        this.isPrivate = isPrivate;
        this.wantToCorrect = wantToCorrect;
        this.wantToImage = wantToImage;
        this.hasImage = hasImage;
        this.writingStep = user.getWritingStep();
        this.imgStatus = imgStatus;
        this.commentStatus = commentStatus;
        setUser(user);
    }

    public String loadSelectedImgUrl() {
        if (getHasImage()) {
            Optional<DiaryImg> diaryImg = getDiaryImgs().stream().
                    filter(img -> img.getIsSelected()).
                    findFirst();
            if(!diaryImg.isPresent()) {
                this.hasImage=false;
                throw new DoranssamException("이미지를 불러올 수 없습니다.", DoranssamErrorCode.INTERNAL_SERVER_ERROR);
            }
            return diaryImg.get().getImgUrl();
        }
        return null;
    }
}
