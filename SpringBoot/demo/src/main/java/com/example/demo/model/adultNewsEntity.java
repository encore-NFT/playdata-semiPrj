package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "adult_news")
public class adultNewsEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")

    private String newsUrl;
    private String newsTitle;
    private String newsDate;
    private String newsImgPath;
    private String newsSource;
}
