package com.nft.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nft.demo.model.KidNewsEntity;

@Repository
public interface NewsRepository<T> extends JpaRepository<T, String> {

    @Query(value = "select kid_news_url, kid_news_title, kid_news_date, kid_news_img, kid_news_source from kid_news "
            + "where year(kid_news_date) = ((select year(max(kid_news_date)) from kid_news)) "
            + "and week(kid_news_date) = ((select week(max(kid_news_date)) from kid_news)) "
            + "and kid_news_article like '%?1%' "
            + "order by kid_news_date desc", nativeQuery = true)
    List<T> findByThisWeekWord(String word);

    @Query(value = "select kid_news_url, kid_news_title, kid_news_date, kid_news_img, kid_news_source from kid_news "
            + "where year(kid_news_date) = year(?1) "
            + "and week(kid_news_date) = week(?1) "
            + "and kid_news_article like %?2% "
            + "order by kid_news_date desc", nativeQuery = true)
    List<T> findByLastWeekWord(String date, String word);

}
