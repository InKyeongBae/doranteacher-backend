package org.triathlongirls.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.triathlongirls.springboot.domain.diaries.Diaries;

public interface DiariesRepository extends JpaRepository<Diaries, Long> {
}
