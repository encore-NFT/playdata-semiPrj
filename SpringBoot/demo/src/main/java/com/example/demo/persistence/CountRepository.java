package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CountEntity;

@Repository
public interface CountRepository extends JpaRepository<CountEntity, String> {

    @Query(value = "select word_list, sum(num) as num from wordcount "
            + "where year(news_date_short) = year(now()) "
            + "and week(news_date_short) = week(now()) "
            + "and num > 1 group by word_list "
            + "order by num desc limit 200 ", nativeQuery = true)
    List<CountEntity> findByNow();


    @Query(value = "select word_list, sum(num) as num from wordcount "
            + "where year(news_date_short) = year(?1) "
            + "and week(news_date_short) = week(?1) "
            + "and num > 1 group by word_list "
            + "order by num desc limit 200 ", nativeQuery = true)
    List<CountEntity> findByDate(String date);

}
