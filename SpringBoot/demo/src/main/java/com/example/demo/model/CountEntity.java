package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @NoArgsConstructor @AllArgsConstructor 
@Data @Entity @Table(name="wordcount")
public class CountEntity {
  	@Id @GeneratedValue(generator="system-uuid")
  	private String wordList;
  	private int num;
}