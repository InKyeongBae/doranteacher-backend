package org.triathlongirls.springboot.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.triathlongirls.springboot.domain.diaries.Diaries;
import org.triathlongirls.springboot.domain.diaries.DiariesRepository;
import org.triathlongirls.springboot.web.dto.DiariesSaveRequestDto;
import org.triathlongirls.springboot.web.dto.DiariesUpdateRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiariesApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DiariesRepository diariesRepository;

    @AfterEach
    public void tearDown() throws Exception {
        diariesRepository.deleteAll();
    }

    @Test
    public void Diaries_등록된다() throws Exception {
        //given
        String title = "title";
        DiariesSaveRequestDto requestDto = DiariesSaveRequestDto.builder()
                .title(title)
                .build();

        String url = "http://localhost:" + port + "/api/v1/diaries";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Diaries> all = diariesRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
    }

    @Test
    public void Diaries_수정된다() throws Exception {
        //given
        Diaries savedDiaries = diariesRepository.save(Diaries.builder()
                .title("title")
                .build());

        Long updateId = savedDiaries.getId();
        String expectedTitle = "title2";

        DiariesUpdateRequestDto requestDto = DiariesUpdateRequestDto.builder()
                .title(expectedTitle)
                .build();

        String url = "http://localhost:" + port + "/api/v1/diaries/" + updateId;

        HttpEntity<DiariesUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Diaries> all = diariesRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
    }
}