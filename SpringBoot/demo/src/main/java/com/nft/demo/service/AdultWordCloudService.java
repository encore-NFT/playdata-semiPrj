package com.nft.demo.service;

import java.util.List;

import com.nft.demo.model.AdultCountEntity;
import com.nft.demo.model.AdultNewsEntity;
import com.nft.demo.persistence.AdultCountRepository;
import com.nft.demo.persistence.AdultNewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdultWordCloudService {
    @Autowired
    private AdultCountRepository countRepository;

    @Autowired
    private AdultNewsRepository newsReopository;

    public List<AdultCountEntity> getThisWeek() {
        return countRepository.findByNow();
    }

    public List<AdultCountEntity> getLastWeek(final String date) {
        return countRepository.findByDate(date);
    }

    public List<AdultNewsEntity> getThisWeekNews(final String word) {
        return newsReopository.findByThisWeekWord(word);
    }

    public List<AdultNewsEntity> getLastWeekNews(final String date, final String word) {
        return newsReopository.findByLastWeekWord(date, word);
    }
}
