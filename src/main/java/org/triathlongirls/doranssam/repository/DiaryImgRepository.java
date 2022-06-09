package org.triathlongirls.doranssam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.domain.diaries.DiaryImg;

import java.util.Optional;

public interface DiaryImgRepository extends JpaRepository<DiaryImg, Long> {
    Optional<DiaryImg> getDiaryImgByDiaryAndIsSelected(Diary diary, Boolean isSelected);
}
