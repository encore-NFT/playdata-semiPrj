package com.example.demo.dto;

import com.example.demo.model.NewsEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsDTO {
	private String id;
	private String news_title;
	private boolean done;
	
	public NewsDTO(final NewsEntity entity) {
		this.id = entity.getId();
		this.news_title = entity.getNews_title();
		this.done = entity.isDone();
	}
}
