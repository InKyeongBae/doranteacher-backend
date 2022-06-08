package org.triathlongirls.doranssam.domain.diaries;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.triathlongirls.doranssam.domain.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "texts")
public class Text extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "text_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String originalText;

    @Column(columnDefinition = "TEXT")
    private String correctText;

    @Column(columnDefinition = "TEXT")
    private String highlightedText;

    private boolean hasSynonym;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    public Text(String originalText, String correctText, String highlightedText, boolean hasSynonym, Diary diary) {
        this.originalText = originalText;
        this.correctText = correctText;
        this.highlightedText = highlightedText;
        this.hasSynonym = hasSynonym;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void saveText(String originalText, String correctText) {
        this.originalText = originalText;
        this.correctText = correctText;
        this.hasSynonym = false;
    }
}

