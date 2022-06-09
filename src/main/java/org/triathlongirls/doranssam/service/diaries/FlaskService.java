package org.triathlongirls.doranssam.service.diaries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.nd4j.linalg.util.LinkedMultiValueMap;
import org.nd4j.linalg.util.MultiValueMap;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.triathlongirls.doranssam.dto.FlaskResponseDto;

@Service
public class FlaskService {

    public FlaskResponseDto requestToFlask(String text) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/recommend";

        // Body set
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("text", text);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        // Combine Message
        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

        // Request and getResponse
        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);

        // Response Body 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        FlaskResponseDto dto = objectMapper.readValue(response.getBody(), FlaskResponseDto.class);

        return dto;
    }

}