package org.triathlongirls.doranssam.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.triathlongirls.doranssam.domain.diaries.DiaryImg;

@Getter @Setter
public class DiaryImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private static ModelMapper modelMapper = new ModelMapper();

    public static DiaryImgDto of(DiaryImg diaryImg) {
        return modelMapper.map(diaryImg, DiaryImgDto.class);
    }
}
