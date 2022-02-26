package com.nft.demo.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "kid_word_count")
public class KidCountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String kidCountWord;
    private int kidCountValue;
}