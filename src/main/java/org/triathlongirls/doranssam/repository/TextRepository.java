package org.triathlongirls.doranssam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.triathlongirls.doranssam.domain.diaries.Text;

public interface TextRepository extends JpaRepository<Text, Long> {
}
