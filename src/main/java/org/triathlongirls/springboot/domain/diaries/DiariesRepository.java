package org.triathlongirls.springboot.domain.diaries;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiariesRepository extends JpaRepository<Diaries, Long> {
}
