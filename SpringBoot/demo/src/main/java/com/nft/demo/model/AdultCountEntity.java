package com.nft.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "adult_word_count")
public class AdultCountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String adultCountWord;
    private int adultCountValue;
}