package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
