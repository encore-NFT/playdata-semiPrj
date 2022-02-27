package com.nft.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nft.demo.model.KidCountEntity;
import com.nft.demo.model.KidNewsEntity;
import com.nft.demo.persistence.CountRepository;
import com.nft.demo.persistence.NewsRepository;

@Service
public class WordCloudService <T>{

    @Autowired
    private CountRepository<T> countRepository;

    @Autowired
    private NewsRepository<T> newsReopository;

    public List<T> getThisWeek() {
        return countRepository.findByNow();
    }

    public List<T> getLastWeek(final String date) {
        return countRepository.findByDate(date);
    }

    public List<T> getThisWeekNews(final String word) {
        return newsReopository.findByThisWeekWord(word);
    }

    public List<T> getLastWeekNews(final String date, final String word) {
        return newsReopository.findByLastWeekWord(date, word);
    }
}
