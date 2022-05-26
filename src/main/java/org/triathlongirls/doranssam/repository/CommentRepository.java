package org.triathlongirls.doranssam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.triathlongirls.doranssam.domain.diaries.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
