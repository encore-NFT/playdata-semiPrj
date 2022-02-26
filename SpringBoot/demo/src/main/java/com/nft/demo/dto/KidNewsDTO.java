package com.nft.demo.dto;
/*
 * @Date : 2022.02.26
 * @version : 1.0
 * @Description : Entity를 DTO로 변환해 리턴
 *                1. 비즈니스 로직 캡슐화
 *                2. 서비스 로직 추가
 */

import com.nft.demo.model.KidNewsEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KidNewsDTO {
    private String kidsNewsUrl;
    private String kidsNewsTitle;
    private String kidsNewsDate;
    private String kidsNewsImg;
    private String kidsNewsSource;

    public KidNewsDTO(final KidNewsEntity entity) {
        this.kidsNewsUrl = entity.getKidNewsUrl();
        this.kidsNewsTitle = entity.getKidNewsTitle();
        this.kidsNewsDate = entity.getKidNewsDate();
        this.kidsNewsImg = entity.getKidNewsImg();
        this.kidsNewsSource = entity.getKidNewsSource();
    }

    public static KidNewsEntity toEntity(final KidNewsDTO dto) {
        return KidNewsEntity.builder()
                .kidNewsUrl(dto.getKidsNewsUrl())
                .kidNewsTitle(dto.getKidsNewsTitle())
                .kidNewsDate(dto.getKidsNewsDate())
                .kidNewsImg(dto.getKidsNewsImg())
                .kidNewsSource(dto.getKidsNewsSource())
                .build();
    }


}
