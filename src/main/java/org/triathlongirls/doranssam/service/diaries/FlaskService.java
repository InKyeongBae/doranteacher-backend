package org.triathlongirls.doranssam.service.diaries;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.nd4j.linalg.util.LinkedMultiValueMap;
import org.nd4j.linalg.util.MultiValueMap;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlaskService {

    public HttpEntity<String> requestToFlask(String text) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5000/recommend";

        // Body set
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("text", text);
        HttpHeaders httpHeaders = new HttpHeaders();
        // Combine Message
        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

        // Request and getResponse
        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);

        return response;
    }

}