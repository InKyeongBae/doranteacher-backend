package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryTypeWord2VecResponse {
    String keyword;
    Map<String, Float> similarlity;
}
