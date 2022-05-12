package org.triathlongirls.doranssam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.triathlongirls.doranssam.domain.diaries.Diaries;

import java.util.Optional;

public interface DiariesRepository extends JpaRepository<Diaries, Long> {
    @Override
    Optional<Diaries> findById(Long aLong);
}
