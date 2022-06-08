package org.triathlongirls.doranssam.service.diaries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.triathlongirls.doranssam.domain.diaries.Comment;
import org.triathlongirls.doranssam.domain.diaries.Text;
import org.triathlongirls.doranssam.repository.TextRepository;

import java.util.List;


@RequiredArgsConstructor
@Service
public class TextService {

    private final TextRepository textRepository;
    private final ObjectMapper objectMapper;

    @Value("${doranssam.api-server.host}")
    public String commentHost;

    @Value("${doranssam.api-server.comment-path}")
    public String commentPath;

    public Text saveText(String original_text, Boolean isPrivate) {
        Text text = new Text();

        if (!isPrivate) {
//            RestTemplate restTemplate = new RestTemplate();
//            String commentUrl = commentHost + commentPath;
//            String jsonText = "{\"text\":\"" + original_text + "\"}";
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<String> request = new HttpEntity<String>(jsonText, headers);
//            String response = restTemplate.postForObject(commentUrl, request, String.class);
//            JsonNode root = objectMapper.readTree(response);
//            String result = root.get("result").toString();

            Comment comment = Comment.builder()
                    .content("오늘 하루도 많은 일이 있었군요. 도란쌤은 언제나 응원하고 있어요.")
                    .build();
            text.setComment(comment);
        }
        text.saveText(original_text, original_text);
        return text;
    }
}
