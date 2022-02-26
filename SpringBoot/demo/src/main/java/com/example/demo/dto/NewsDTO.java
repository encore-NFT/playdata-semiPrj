package com.example.demo.dto;

import com.example.demo.model.NewsEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsDTO {
    private String newsUrl;
    private String newsTitle;
    private String newsDate;
    private String newsImgPath;
    private String newsSource;

    public NewsDTO(final NewsEntity entity) {
        this.newsUrl = entity.getNewsUrl();
        this.newsTitle = entity.getNewsTitle();
        this.newsDate = entity.getNewsDate();
        this.newsImgPath = entity.getNewsImgPath();
        this.newsSource = entity.getNewsSource();
    }

    public static NewsEntity toEntity(final NewsDTO dto) {
        return NewsEntity.builder()
                .newsUrl(dto.getNewsUrl())
                .newsTitle(dto.getNewsTitle())
                .newsDate(dto.getNewsDate())
                .newsImgPath(dto.getNewsImgPath())
                .newsSource(dto.getNewsSource())
                .build();
    }
}
