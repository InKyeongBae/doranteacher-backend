package org.triathlongirls.doranssam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
public class DiaryCatalogDetailDto {

    private Long diary_id;

    private LocalDate diary_date;

    private String imgUrl;

}
