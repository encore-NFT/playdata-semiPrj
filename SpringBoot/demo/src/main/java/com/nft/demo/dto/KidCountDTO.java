package com.nft.demo.dto;

import com.nft.demo.model.KidCountEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KidCountDTO {
    private String kidCountWord;
    private int kidCountValue;

    public KidCountDTO(final KidCountEntity entity) {
        this.kidCountWord = entity.getKidCountWord();
        this.kidCountValue = entity.getKidCountValue();
    }

    public static KidCountEntity toEntity(final KidCountDTO dto) {
        return KidCountEntity.builder()
                .kidCountWord(dto.getKidCountWord())
                .kidCountValue(dto.getKidCountValue())
                .build();
    }
}