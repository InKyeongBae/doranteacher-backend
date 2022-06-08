package org.triathlongirls.doranssam.service.diaries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.glassfish.jersey.message.internal.AcceptableMediaType;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
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

//    @Value("${doranssam.api-server.host}")
    private String commentHost = "http://tool.doranssam.com:5000";
//
//    @Value("${doranssam.api-server.comment-path}")
    private String commentPath = "/correct";

    public Text saveText(
            String originalText,
            List<String> correctText,
            String comment,
            Boolean isPrivate) {

        Text text = new Text();
        if (!isPrivate) {
//            RestTemplate restTemplate = new RestTemplate();
//            String commentUrl = commentHost + commentPath;
//            String jsonText = "{\"text\":\"" + original_text + "\"}";
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setAccept(List.of(MediaType.ALL));
//
//            HttpEntity<String> request = new HttpEntity<>(jsonText, headers);
//            ResponseEntity<String> response = restTemplate.exchange(commentUrl, HttpMethod.POST, request, String.class);
//            JsonNode root = objectMapper.readTree(response.getBody());
//            String result = root.get("result").toString();

            Comment newComment = Comment.builder()
                    .content(comment)
                    .build();
            text.setComment(newComment);
        }
        text.saveText(originalText, correctText);
        return text;
    }
}
