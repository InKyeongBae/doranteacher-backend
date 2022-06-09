package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.triathlongirls.doranssam.constant.DiaryQuestion;
import org.triathlongirls.doranssam.constant.DiaryType;
import org.triathlongirls.doranssam.dto.DiaryTypeRecommendResult;
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

    public List<DiaryTypeRecommendResult> recommendTempDiaryType(String keywords) {
        List<String> keywordList = Arrays.asList(keywords.split(","));

        List<String> diaryTypeList = new ArrayList<>();
        if (keywordList.contains("학교")) {
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
        } else {
            diaryTypeList = Stream.of(DiaryType.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            Collections.shuffle(diaryTypeList);
        }

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
