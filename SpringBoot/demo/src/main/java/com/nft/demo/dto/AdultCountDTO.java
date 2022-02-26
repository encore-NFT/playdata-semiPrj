package com.nft.demo.dto;

import com.nft.demo.model.AdultCountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdultCountDTO {
    private String adultCountWord;
    private int adultCountValue;

    public AdultCountDTO(final AdultCountEntity entity) {
        this.adultCountWord = entity.getAdultCountWord();
        this.adultCountValue = entity.getAdultCountValue();
    }

    public static AdultCountEntity toEntity(final AdultCountDTO dto) {
        return AdultCountEntity.builder()
                .adultCountWord(dto.getAdultCountWord())
                .adultCountValue(dto.getAdultCountValue())
                .build();
    }
}