package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.domain.user.User;
import org.triathlongirls.doranssam.dto.DiaryDetailResponseDto;
import org.triathlongirls.doranssam.dto.DiarySaveResponseDto;
import org.triathlongirls.doranssam.dto.DiarySaveRequestDto;
import org.triathlongirls.doranssam.exception.EntityNotFoundException;
import org.triathlongirls.doranssam.repository.DiaryRepository;
import org.triathlongirls.doranssam.service.user.UserService;
import org.triathlongirls.doranssam.util.SecurityUtil;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final UserService userService;
    private final DiaryRepository diaryRepository;

    @Transactional
    public DiarySaveResponseDto save(DiarySaveRequestDto requestDto) {
        String username = SecurityUtil.getCurrentUsername();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.: " + username));
        return DiarySaveResponseDto.of(diaryRepository.save(requestDto.toEntity(user)));
    }
/*
    @Transactional
    public Long update(Long id, DiariesUpdateRequestDto requestDto) {
        Diaries diaries = diariesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일기가 없습니다. id=" + id));
        //diaries.update(requestDto.getTitle());

        return id;
    }
*/
    public DiaryDetailResponseDto findById(Long id) {
        Diary entity = diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 일기가 없습니다. id=" + id));

        return DiaryDetailResponseDto.of(entity);
    }


    public Object deleteById(Long id) {
        Diary entity = diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 일기가 없습니다. id=" + id));
        diaryRepository.deleteById(entity.getId());
        return true;
    }
}
