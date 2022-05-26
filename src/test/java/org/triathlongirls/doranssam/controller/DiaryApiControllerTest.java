/*
package org.triathlongirls.doranssam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.triathlongirls.doranssam.constant.DiaryType;
import org.triathlongirls.doranssam.domain.diaries.Diaries;
import org.triathlongirls.doranssam.domain.diaries.Diary;
import org.triathlongirls.doranssam.dto.DiariesUpdateRequestDto;
import org.triathlongirls.doranssam.dto.DiarySaveRequestDto;
import org.triathlongirls.doranssam.repository.DiaryRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebAppConfiguration
@ActiveProfiles("test")
@SpringBootTest
public class DiariesApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DiaryRepository diaryRepository;

    @AfterEach
    public void tearDown(){
        diaryRepository.deleteAll();
    }

    public Diary createDiary() {
        DiarySaveRequestDto dto = new DiarySaveRequestDto(
                "title",
                LocalDate.now(),
                "weather",
                "keywords",
                "text",
                DiaryType.FREE,
                false,
                true,
                false
        );
        return dto.toEntity();
    }

    @Test
    @DisplayName("일기 등록 성공")
    public void successCreateDiary() throws Exception {

        String body = objectMapper.writeValueAsString(
                new DiarySaveRequestDto(
                    "title",
                    LocalDate.now(),
                    "weather",
                    "keywords",
                    "text",
                    DiaryType.FREE,
                    false,
                    true,
                    false
                )
        );

        mockMvc.perform(
                post("/diaries")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andDo(print());
    }

    @Test
    @DisplayName("일기 삭제 성공")
    public void successDeleteDiary() throws Exception {
        Diary savedDiaries = diaryRepository.save(Diary.builder()
                .title("title")
                .build());

        Long updateId = savedDiary.getId();
        String expectedTitle = "title2";

        DiaryUpdateRequestDto requestDto = DiaryUpdateRequestDto.builder()
                .title(expectedTitle)
                .build();

        String url = "http://localhost:" + port + "/api/v1/diaries/" + updateId;

        HttpEntity<DiaryUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Diary> all = diaryRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
    }
}
*/