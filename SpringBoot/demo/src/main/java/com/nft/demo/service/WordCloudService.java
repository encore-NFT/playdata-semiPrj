package com.nft.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nft.demo.model.KidCountEntity;
import com.nft.demo.model.KidNewsEntity;
import com.nft.demo.persistence.CountRepository;
import com.nft.demo.persistence.NewsRepository;

@Service //@Slf4j
public class WordCloudService {

    @Autowired
    private CountRepository countRepository;
    @Autowired
    private NewsRepository newsReopository;

    public List<KidCountEntity> getThisWeek() {
        return countRepository.findByNow();
    }

    public List<KidCountEntity> getLastWeek(final String date) {
        return countRepository.findByDate(date);
    }

    public List<KidNewsEntity> getThisWeekNews(final String word) {
        return newsReopository.findByThisWeekWord(word);
    }

    public List<KidNewsEntity> getLastWeekNews(final String date, final String word) {
        return newsReopository.findByLastWeekWord(date, word);
    }

}
