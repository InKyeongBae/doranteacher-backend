package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.triathlongirls.doranssam.repository.TextRepository;


@RequiredArgsConstructor
@Service
public class TextService {
    private final TextRepository textRepository;

}
