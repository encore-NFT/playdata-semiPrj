package com.nft.demo.model;

/*
 * @Date : 2022.02.26
 * @version : 1.0
 * @Description : 비즈니스 데이터, DB의 Table 스키마 표현
 */

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // 생성자를 이용해 오브젝트 생성
@NoArgsConstructor // 매개변수 없는 생성자 구현
@AllArgsConstructor // 모든 멤버 변수를 매개변수로 받는 생성자 구현
@Data // Getter,Setter 메서드 구현
@Entity
@Table(name = "kid_news")
public class KidNewsEntity {
    @Id // 기본 키가 될 필드에 지정
//    @GeneratedValue(generator = "system-uuid") // 자동으로 생성 - 커스텀
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 값을 null로 하면 DB가 알아서 AUTO_INCREMENT

    private String kidNewsUrl; // 기사 Url
    private String kidNewsTitle; // 기사 제목
    private String kidNewsDate; // 기사 날짜
    private String kidNewsImg; // 기사 이미지
    private String kidNewsSource; // 기사 출처

}
