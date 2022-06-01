package org.triathlongirls.doranssam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.triathlongirls.doranssam.domain.diaries.Diary;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Override
    Optional<Diary> findById(Long aLong);

    @Query(value = "select *  " +
            "from diaries d " +
            "where year(d.date) = ?1 and month(d.date) =?2 and d.user_id =?3 " +
            "order by  d.date"
            , nativeQuery = true)
    List<Diary> findByYearMonth(Integer year, Integer month, Long userId);
}
