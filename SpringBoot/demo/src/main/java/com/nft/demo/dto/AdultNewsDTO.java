package com.nft.demo.dto;
/*
 * @Date : 2022.02.26
 * @version : 1.0
 * @Description : Entity를 DTO로 변환해 리턴
 *                1. 비즈니스 로직 캡슐화
 *                2. 서비스 로직 추가
 */

import com.nft.demo.model.AdultNewsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdultNewsDTO {
    private String adultNewsUrl;
    private String adultNewsTitle;
    private String adultNewsDate;
    private String adultNewsImg;
    private String adultNewsSource;

    public AdultNewsDTO(final AdultNewsEntity entity) {
        this.adultNewsUrl = entity.getAdultNewsUrl();
        this.adultNewsTitle = entity.getAdultNewsTitle();
        this.adultNewsDate = entity.getAdultNewsDate();
        this.adultNewsImg = entity.getAdultNewsImg();
        this.adultNewsSource = entity.getAdultNewsSource();
    }

    public static AdultNewsEntity toEntity(final AdultNewsDTO dto) {
        return AdultNewsEntity.builder()
                .adultNewsUrl(dto.getAdultNewsUrl())
                .adultNewsTitle(dto.getAdultNewsTitle())
                .adultNewsDate(dto.getAdultNewsDate())
                .adultNewsImg(dto.getAdultNewsImg())
                .adultNewsSource(dto.getAdultNewsSource())
                .build();
    }


}
