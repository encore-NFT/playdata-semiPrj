package com.example.demo.dto;

import com.example.demo.model.CountEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class CountDTO {
  	private String wordList;
  	private int num;

  	public CountDTO(final CountEntity entity) {
  		this.wordList = entity.getWordList();
      	this.num = entity.getNum();
    }
  
  	public static CountEntity toEntity(final CountDTO dto) {
      	return CountEntity.builder()
          	.wordList(dto.getWordList())
          	.num(dto.getNum())
          	.build();
    }
}