package org.triathlongirls.doranssam.domain.diaries;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.triathlongirls.doranssam.exception.EntityNotFoundException;
import org.triathlongirls.doranssam.repository.CommentRepository;
import org.triathlongirls.doranssam.repository.TextRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles(profiles = "test")
class TextTest {

    @Autowired
    TextRepository textRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void findTextAndComment() {
        Comment comment = new Comment();
        comment.setText("comment"); //TODO: comment AI api
        commentRepository.save(comment);

        Text text = Text.builder()
                .originalText("originalText")
                .correctText("correctText")
                .highlightedText("highlightedText")
                .hasSynonym(true)
                .build();
        text.setComment(comment);
        textRepository.save(text);

        Text savedText = textRepository.findById(text.getId())
                .orElseThrow(() -> new EntityNotFoundException("해당 일기글이 없습니다. id=" + text.getId()));
        assertThat(savedText.getComment().getId()).isEqualTo(comment.getId());
    }
}