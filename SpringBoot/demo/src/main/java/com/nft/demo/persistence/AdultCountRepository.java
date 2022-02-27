package com.nft.demo.persistence;

import com.nft.demo.model.AdultCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdultCountRepository extends JpaRepository<AdultCountEntity, String> {

    @Query(value = "select adult_count_word, sum(adult_count_value) as adult_count_value "
            + "from adult_word_count "
            + "where year(adult_count_date) = ((select year(max(adult_count_date)) from adult_word_count)) "
            + "and week(adult_count_date) = ((select week(max(adult_count_date)) from adult_word_count)) "
            + "and adult_count_value > 1 group by adult_count_word "
            + "order by adult_count_value desc limit 200 ", nativeQuery = true)
    List<AdultCountEntity> findByNow();

    @Query(value = "select adult_count_word, sum(adult_count_value) as adult_count_value "
            + "from adult_word_count "
            + "where year(adult_count_date) = year(?1) "
            + "and week(adult_count_date) = week(?1) "
            + "and adult_count_value > 1 group by adult_count_word "
            + "order by adult_count_value desc limit 200 ", nativeQuery = true)
    List<AdultCountEntity> findByDate(String date);

}
