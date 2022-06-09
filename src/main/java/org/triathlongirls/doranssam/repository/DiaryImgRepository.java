package org.triathlongirls.doranssam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.domain.diaries.DiaryImg;

import java.util.List;
import java.util.Optional;

public interface DiaryImgRepository extends JpaRepository<DiaryImg, Long> {
    List<DiaryImg> findDiaryImgsByDiaryAndIsSelected(Diary diary, Boolean isSelected);
}
