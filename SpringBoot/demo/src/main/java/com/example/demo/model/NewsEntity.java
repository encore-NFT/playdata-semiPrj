package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsEntity {
	private String id;
	private String news_url;
	private String news_title;
	private String news_author;
	private String news_date;
	private String news_article;
	private String news_img_path;
	private String news_source;
	private boolean done;		//true - 처리가 완료된 경
}
