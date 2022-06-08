package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.triathlongirls.doranssam.constant.DiaryQuestion;
import org.triathlongirls.doranssam.constant.DiaryType;
import org.triathlongirls.doranssam.dto.DiaryTypeRecommendResult;

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

        List<DiaryTypeRecommendResult> results = new ArrayList<>();
//        try {
//            HashMap<String, Double> wordMap = Word2VecUtil.calculateSimilarity(diaryTypeList, keywordList);
//            Iterator<Map.Entry<String, Double>> entries = wordMap.entrySet().iterator();
//            while (entries.hasNext()) {
//                Map.Entry<String, Double> entry = entries.next();
//                results.add(new DiaryTypeRecommendResult(
//                        entry.getKey(),
//                        entry.getValue()
//                ));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        return results;
    }

    public List<DiaryTypeRecommendResult> recommendTempDiaryType(String keywords) {
        List<String> keywordList = Arrays.asList(keywords.split(","));
        List<String> diaryTypeList = Stream.of(DiaryType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

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
