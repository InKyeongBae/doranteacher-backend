package org.triathlongirls.doranssam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.dto.DiaryBookCountInterface;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Override
    Optional<Diary> findById(Long id);

    @Query(value = "select *  " +
            "from diaries d " +
            "where year(d.date) = year(CURRENT_DATE()) and " +
            "month(d.date) = month(CURRENT_DATE()) and " +
            "d.user_id =?1 " +
            "order by  d.date"
            , nativeQuery = true)
    List<Diary> findByYearMonth(Integer year, Integer month, Long userId);

    @Query(value = "select *  " +
            "from diaries d " +
            "where year(d.date) = ?1 and month(d.date) =?2 and d.user_id =?3 " +
            "order by  d.date"
            , nativeQuery = true)
    List<Diary> findDiaryThisMonth(Long userId);

    @Query(value = "select DATE_FORMAT(d.date, '%Y-%m') as date, count(*) as diaryCount " +
            "from diaries d " +
            "where d.user_id = ?1  and month(d.date) < month(CURRENT_DATE()) " +
            "GROUP BY DATE_FORMAT(d.date, '%Y-%m') " +
            "order by DATE_FORMAT(d.date, '%Y-%m') DESC"
            , nativeQuery = true)
    List<DiaryBookCountInterface> countDiaryByYearMonth(Long userId);
}
