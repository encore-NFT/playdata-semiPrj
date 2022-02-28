package com.nft.demo.persistence;

import com.nft.demo.model.AdultNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdultNewsRepository extends JpaRepository<AdultNewsEntity, String> {

    @Query(value = "select adult_news_url, adult_news_title, adult_news_date, adult_news_source from adult_news "
            + "where year(adult_news_date) = ((select year(max(adult_news_date)) from adult_news)) "
            + "and week(adult_news_date) = ((select week(max(adult_news_date)) from adult_news)) "
            + "and adult_news_article like '%?1%' "
            + "order by adult_news_date desc", nativeQuery = true)
    List<AdultNewsEntity> findByThisWeekWord(String word);

    @Query(value = "select adult_news_url, adult_news_title, adult_news_date, adult_news_source from adult_news "
            + "where year(adult_news_date) = year(?1) "
            + "and week(adult_news_date) = week(?1) "
            + "and adult_news_article like '%?2%' "
            + "order by adult_news_date desc", nativeQuery = true)
    List<AdultNewsEntity> findByLastWeekWord(String date, String word);

}
