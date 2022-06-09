package org.triathlongirls.doranssam.domain.diaries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "diary_img")
public class DiaryImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_img_id")
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private Boolean isSelected;
    private Integer xCoordinate;
    private Integer yCoordinate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public void updateDiaryImg(
            String oriImgName,
            String imgName,
            String imgUrl,
            Integer xCoordinate,
            Integer yCoordinate) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.isSelected = true;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public void selectDiaryImg(
            Integer xCoordinate,
            Integer yCoordinate
    ) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.isSelected = true;
    }
}
