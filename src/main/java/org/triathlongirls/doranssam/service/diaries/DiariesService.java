package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.triathlongirls.doranssam.domain.diaries.Diaries;
import org.triathlongirls.doranssam.domain.user.User;
import org.triathlongirls.doranssam.dto.DiariesDetailResponseDto;
import org.triathlongirls.doranssam.dto.DiariesSaveResponseDto;
import org.triathlongirls.doranssam.dto.DiariesSaveRequestDto;
import org.triathlongirls.doranssam.exception.EntityNotFoundException;
import org.triathlongirls.doranssam.repository.DiariesRepository;
import org.triathlongirls.doranssam.service.user.UserService;
import org.triathlongirls.doranssam.util.SecurityUtil;

@RequiredArgsConstructor
@Service
public class DiariesService {
    private final UserService userService;
    private final DiariesRepository diariesRepository;

    @Transactional
    public DiariesSaveResponseDto save(DiariesSaveRequestDto requestDto) {
        String username = SecurityUtil.getCurrentUsername();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.: " + username));
        return DiariesSaveResponseDto.of(diariesRepository.save(requestDto.toEntity(user)));
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
    public DiariesDetailResponseDto findById(Long id) {
        Diaries entity = diariesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 일기가 없습니다. id=" + id));

        return DiariesDetailResponseDto.of(entity);
    }


    public Object deleteById(Long id) {
        Diaries entity = diariesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 일기가 없습니다. id=" + id));
        diariesRepository.deleteById(entity.getId());
        return true;
    }
}
