package com.nft.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nft.demo.model.KidCountEntity;

@Repository
public interface CountRepository extends JpaRepository<KidCountEntity, String> {

    @Query(value = "select kid_count_word, sum(kid_count_value) as kid_count_value "
            + "from kid_word_count "
            + "where year(kid_count_date) = year(now()) "
            + "and week(kid_count_date) = week(now()) "
            + "and kid_count_value > 1 group by kid_count_word "
            + "order by kid_count_value desc limit 200 ", nativeQuery = true)
    List<KidCountEntity> findByNow();

    @Query(value = "select kid_count_word, sum(kid_count_value) as kid_count_value "
            + "from kid_word_count "
            + "where year(kid_count_date) = year(?1) "
            + "and week(kid_count_date) = week(?1) "
            + "and kid_count_value > 1 group by kid_count_word "
            + "order by kid_count_value desc limit 200 ", nativeQuery = true)
    List<KidCountEntity> findByDate(String date);

}
