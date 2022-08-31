package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import org.nd4j.linalg.util.LinkedMultiValueMap;
import org.nd4j.linalg.util.MultiValueMap;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.triathlongirls.doranssam.constant.DiaryQuestion;
import org.triathlongirls.doranssam.constant.DiaryType;
import org.triathlongirls.doranssam.dto.DiaryTypeRecommendResult;
import org.triathlongirls.doranssam.dto.DiaryTypeWord2VecRequest;
import org.triathlongirls.doranssam.dto.DiaryTypeWord2VecResponse;
import org.triathlongirls.doranssam.util.dl4j.Word2VecUtil;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class DiaryTypeService {


    public List<DiaryTypeRecommendResult> recommendDiaryType(String keywords) {
        List<String> keywordList = Arrays.asList(keywords.split(","));
        List<String> diaryTypeList = Stream.of(DiaryType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        List<DiaryTypeRecommendResult> recommendResults = new ArrayList<>();
        try {
            HashMap<String, Double> wordMap = Word2VecUtil.calculateSimilarity(diaryTypeList, keywordList);
            Iterator<Map.Entry<String, Double>> entries = wordMap.entrySet().iterator();

            List<Map.Entry<String, Double>> results = new ArrayList<>();
            while (entries.hasNext()) {
                results.add(entries.next());
            }
            // 계산 결과 정렬
            results.sort((o1, o2) -> (int) (o1.getValue() - o2.getValue()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return recommendResults;
    }

    public List<DiaryTypeRecommendResult> recommendDiaryTypeFromWord2VecServer(String keywords) {
        List<String> keywordList = Arrays.asList(keywords.split(","));
        List<String> diaryTypeList = Stream.of(DiaryType.values())
                .map(type -> type.name().substring(0,2))
                .collect(Collectors.toList());
//        List<String> diaryTypes = Arrays.asList("배움", "여행", "과학", "편지", "독서", "관찰", "소식", "시청", "사물", "감상", "칭찬", "효도", "사건", "요리", "환경", "자유");

        DiaryTypeWord2VecRequest diaryTypeWord2VecRequest = DiaryTypeWord2VecRequest.builder()
                .diaryTypes(diaryTypeList)
                .keywords(keywordList)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://word2vec.doranssam.com:8000/word2vec/recommend-diary-type";
//        String url = "http://localhost:8000/word2vec/recommend-diary-type";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DiaryTypeWord2VecRequest> request = new HttpEntity<>(diaryTypeWord2VecRequest, headers);

        ResponseEntity<DiaryTypeWord2VecResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                DiaryTypeWord2VecResponse.class);

        Map<String, Float> results = response.getBody().getSimilarlity();
        // 계산 결과 정렬
        List<Map.Entry<String, Float>> sorted_result = new ArrayList<>(results.entrySet());
        sorted_result.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<DiaryTypeRecommendResult> recommendResults = new ArrayList<>();
        int rank = 1;
        for (Map.Entry<String, Float> r : sorted_result) {
            recommendResults.add(new DiaryTypeRecommendResult(r.getKey() + "일기", rank));
            rank++;
        }
        return recommendResults;
    }

    public List<DiaryTypeRecommendResult> recommendTempDiaryType(String keywords) {
        List<String> keywordList = Arrays.asList(keywords.split(","));

        List<String> diaryTypeList = new ArrayList<>();
        diaryTypeList.add(DiaryType.배움일기.toString());
        diaryTypeList.add(DiaryType.여행일기.toString());
        diaryTypeList.add(DiaryType.과학일기.toString());
        diaryTypeList.add(DiaryType.편지일기.toString());
        diaryTypeList.add(DiaryType.독서일기.toString());
        diaryTypeList.add(DiaryType.관찰일기.toString());
        diaryTypeList.add(DiaryType.소식일기.toString());
        diaryTypeList.add(DiaryType.시청일기.toString());
        diaryTypeList.add(DiaryType.사물일기.toString());
        diaryTypeList.add(DiaryType.감상일기.toString());
        diaryTypeList.add(DiaryType.칭찬일기.toString());
        diaryTypeList.add(DiaryType.효도일기.toString());
        diaryTypeList.add(DiaryType.사건일기.toString());
        diaryTypeList.add(DiaryType.요리일기.toString());
        diaryTypeList.add(DiaryType.환경일기.toString());
        diaryTypeList.add(DiaryType.자유일기.toString());

        List<DiaryTypeRecommendResult> results = new ArrayList<>();
        Integer id = 1;
        for (String type:diaryTypeList) {
            results.add(new DiaryTypeRecommendResult(type, id++));
        }

        return results;
    }

    public String getStep2Questions(String type) {
        return DiaryQuestion.getStep2Question(DiaryType.valueOf(type));
    }

    public List<String> getStep1Questions(String type) {
        return DiaryQuestion.getStep1Question(DiaryType.valueOf(type));
    }
}
