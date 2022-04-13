package org.triathlongirls.springboot.domain.diaries;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Diaries extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false)
    private String title;

    @Builder
    public Diaries(String title) {
        this.title = title;
    }

    public void update(String title) {
        this.title = title;
    }
//    최종 디비설계안 보고 추가필요

}
