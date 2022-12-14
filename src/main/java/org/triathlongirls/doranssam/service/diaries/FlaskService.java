package org.triathlongirls.doranssam.service.diaries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nd4j.linalg.util.LinkedMultiValueMap;
import org.nd4j.linalg.util.MultiValueMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlaskService {

    public String requestToFlask(String text) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:4000/recommend";

        // Body set
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("text", text);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        // Combine Message
        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);
        System.out.println(requestMessage);
        System.out.println("!!!");
        System.out.println(url);
        // Request and getResponse
        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(response.getBody());


        // Response Body 파싱
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//        FlaskResponseDto dto = objectMapper.readValue(response.getBody(), FlaskResponseDto.class);

        return jsonInString;
    }

}