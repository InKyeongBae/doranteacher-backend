package org.triathlongirls.doranssam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.triathlongirls.doranssam.domain.diaries.Diary;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Override
    Optional<Diary> findById(Long aLong);
}
