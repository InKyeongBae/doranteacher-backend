package org.triathlongirls.doranssam.service.diaries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.triathlongirls.doranssam.constant.DiaryType;
import org.triathlongirls.doranssam.dto.DiaryTypeRecommendResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class DiaryTypeService {


    public List<DiaryTypeRecommendResult> recommendDiaryType(String keywords) {
        String[] keywordList = keywords.split(",");

        List<DiaryTypeRecommendResult> results = new ArrayList<>();
        List<String> diaryTypeList = Stream.of(DiaryType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        Integer rank = 1;
        for (String diaryType: diaryTypeList) {
            results.add(new DiaryTypeRecommendResult(
                    diaryType,
                    rank
            ));
            rank++;
        }

        return results;
    }
}
