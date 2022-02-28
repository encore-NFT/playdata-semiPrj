package com.nft.demo.model;

/*
 * @Date : 2022.02.26
 * @version : 1.0
 * @Description : 어른이 뉴스 엔티티
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder // 생성자를 이용해 오브젝트 생성
@NoArgsConstructor // 매개변수 없는 생성자 구현
@AllArgsConstructor // 모든 멤버 변수를 매개변수로 받는 생성자 구현
@Data // Getter,Setter 메서드 구현
@Entity
@Table(name = "adult_news")
public class AdultNewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String adultNewsUrl; // 기사 Url
    private String adultNewsTitle; // 기사 제목
    private String adultNewsDate; // 기사 날짜
//    private String adultNewsImg; // 기사 이미지
    private String adultNewsSource; // 기사 출처

}
