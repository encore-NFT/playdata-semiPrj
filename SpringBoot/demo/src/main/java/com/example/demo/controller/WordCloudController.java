package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CountDTO;
import com.example.demo.dto.NewsDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.CountEntity;
import com.example.demo.model.NewsEntity;
import com.example.demo.service.WordCloudService;

@RestController @RequestMapping("wordcloud")
public class WordCloudController {
	@Autowired
	private WordCloudService service;
	
	@GetMapping("/thisweek")
	public ResponseEntity<?> getThisWeekWC() {
		List<CountEntity> entities = service.getThisWeek();
		
		List<CountDTO> dtos = entities.stream()
				.map(CountDTO::new)
				.collect(Collectors.toList());
		
		ResponseDTO<CountDTO> response = ResponseDTO.<CountDTO>builder()
				.data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("lastweek/{date}")
	public ResponseEntity<?> getLastWeekWC(@PathVariable(required=false) String date) {
		List<CountEntity> entities = service.getLastWeek(date);
		
		List<CountDTO> dtos = entities.stream()
				.map(CountDTO::new)
				.collect(Collectors.toList());
		
		ResponseDTO<CountDTO> response = ResponseDTO.<CountDTO>builder()
				.data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("thisweek/{word}")
	public ResponseEntity<?> getThisWeekNewsList(@PathVariable String word) {
		
		List<NewsEntity> entities = service.getThisWeekNews(word);
		
		List<NewsDTO> dtos = entities.stream()
				.map(NewsDTO::new)
				.collect(Collectors.toList());
		
		ResponseDTO<NewsDTO> response = ResponseDTO.<NewsDTO>builder()
				.data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("lastweek/{date}/{word}")
	public ResponseEntity<?> getLastWeekNewsList(
		@PathVariable String date, @PathVariable(required=false) String word) {
		
		List<NewsEntity> entities = service.getLastWeekNews(date, word);
		
		List<NewsDTO> dtos = entities.stream()
				.map(NewsDTO::new)
				.collect(Collectors.toList());
		
		ResponseDTO<NewsDTO> response = ResponseDTO.<NewsDTO>builder()
				.data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}

}
