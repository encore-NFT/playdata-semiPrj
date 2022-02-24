package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CountEntity;
import com.example.demo.model.NewsEntity;
import com.example.demo.persistence.CountRepository;
import com.example.demo.persistence.NewsRepository;
 
@Service //@Slf4j
public class WordCloudService {
	
	@Autowired
	private CountRepository countRepository;
	@Autowired
	private NewsRepository newsReopository;
	
	public List<CountEntity> getThisWeek() {
		return countRepository.findByNow();
	}
	
	public List<CountEntity> getLastWeek(final String date) {	
		return countRepository.findByDate(date);
	}
	
	public List<NewsEntity> getThisWeekNews(final String word) {
		return newsReopository.findByThisWeekWord(word);
	}
	
	public List<NewsEntity> getLastWeekNews(final String date, final String word) {
		return newsReopository.findByLastWeekWord(date, word);
	}
	
}
