package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.triathlongirls.doranssam.domain.diaries.Diaries;
import org.triathlongirls.doranssam.repository.DiariesRepository;
import org.triathlongirls.doranssam.web.dto.DiariesResponseDto;
import org.triathlongirls.doranssam.web.dto.DiariesSaveRequestDto;
import org.triathlongirls.doranssam.web.dto.DiariesUpdateRequestDto;

@RequiredArgsConstructor
@Service
public class DiariesService {
    private final DiariesRepository diariesRepository;

    @Transactional
    public Long save(DiariesSaveRequestDto requestDto) {
        return diariesRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, DiariesUpdateRequestDto requestDto) {
        Diaries diaries = diariesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일기가 없습니다. id=" + id));
        //diaries.update(requestDto.getTitle());

        return id;
    }

    public DiariesResponseDto findById(Long id) {
        Diaries entity = diariesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일기가 없습니다. id=" + id));

        return new DiariesResponseDto(entity);
    }
}
