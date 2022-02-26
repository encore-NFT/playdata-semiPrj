package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.NewsEntity;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, String> {

    @Query(value = "select news_url, news_title, news_date, news_img_path, news_source from kidnews "
            + "where year(news_date) = year(now()) "
            + "and week(news_date) = week(now()) "
            + "and news_article like '%?1%' "
            + "order by news_date desc", nativeQuery = true)
    List<NewsEntity> findByThisWeekWord(String word);

    @Query(value = "select news_url, news_title, news_date, news_img_path, news_source from kidnews "
            + "where year(news_date) = year(?1) "
            + "and week(news_date) = week(?1) "
            + "and news_article like %?2% "
            + "order by news_date desc", nativeQuery = true)
    List<NewsEntity> findByLastWeekWord(String date, String word);

}
