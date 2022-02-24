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
@Data @Entity @Table(name="kidnews")
public class NewsEntity {
	@Id @GeneratedValue(generator="system-uuid")
	
	private String newsUrl;
  	private String newsTitle;
  	private String newsDate;
  	private String newsImgPath;
  	private String newsSource;
}
