package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // 생성자를 이용해 오브젝트 생성
@NoArgsConstructor // 매개변수 없는 생성자 구현
@AllArgsConstructor // 모든 멤버 변수를 매개변수로 받는 생성자 구현
@Data // Getter,Setter 메서드 구현
@Entity
@Table(name = "kidnews")
public class NewsEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")

    private String newsUrl; // 기사 Url
    private String newsTitle; // 기사 제목
    private String newsDate; // 기사 날짜
    private String newsImgPath; // 기사 이미지
    private String newsSource; // 기사 출처
}
