package org.triathlongirls.doranssam.domain.diaries;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.triathlongirls.doranssam.repository.DiariesRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DiariesRepositoryTest {

    @Autowired
    DiariesRepository diariesRepository;

    @AfterEach
    public void cleanup() {
        diariesRepository.deleteAll();
    }

    @Test
    public void 일기저장_불러오기() {
        //given
        String title = "테스트 일기";

        diariesRepository.save(Diaries.builder()
                .title(title)
                .build());

        //when
        List<Diaries> diariesList = diariesRepository.findAll();

        //then
        Diaries diaries = diariesList.get(0);
        assertThat(diaries.getTitle()).isEqualTo(title);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2022, 3, 31, 0, 0, 0);
        diariesRepository.save(Diaries.builder()
                .title("title")
                .build());

        //when
        List<Diaries> diariesList = diariesRepository.findAll();

        //then
        Diaries diaries = diariesList.get(0);

        System.out.println(">>>>>>>> created_at= " + diaries.getCreated_at()+", updated_at="+diaries.getUpdated_at());

        assertThat(diaries.getCreated_at()).isAfter(now);
        assertThat(diaries.getUpdated_at()).isAfter(now);
    }
}
