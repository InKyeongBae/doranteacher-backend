package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryTypeWord2VecRequest {
    List<String> keywords;
    List<String> diaryTypes;
}
