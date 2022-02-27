package com.nft.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// RestController : http와 관련된 코드 및 요청/응답 매핑을 해줌

import com.nft.demo.dto.KidCountDTO;
import com.nft.demo.dto.KidNewsDTO;
import com.nft.demo.dto.ResponseDTO;
import com.nft.demo.model.KidCountEntity;
import com.nft.demo.model.KidNewsEntity;
import com.nft.demo.service.WordCloudService;

// http://localhost:80/wordcloud
@CrossOrigin(origins = "http://localhost:3000") // React 연결
@RestController
@RequestMapping("wordcloud")
public class WordCloudController {

    @Autowired //자바빈을 찾아서 인스턴스 멤버 변수에 연결
    private WordCloudService service;

    @GetMapping("/thisweek")
    public ResponseEntity<?> getThisWeekWC() {
        List<KidCountEntity> entities = service.getThisWeek();

        List<KidCountDTO> dtos = entities.stream()
                .map(KidCountDTO::new)
                .collect(Collectors.toList());

        ResponseDTO<KidCountDTO> response = ResponseDTO.<KidCountDTO>builder()
                .data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("lastweek/{date}") // @PathVariable : URI의 경로로 넘어오는 값을 변수로 받을 수 있다.
    public ResponseEntity<?> getLastWeekWC(@PathVariable(required = false) String date) {
        List<KidCountEntity> entities = service.getLastWeek(date);

        List<KidCountDTO> dtos = entities.stream()
                .map(KidCountDTO::new)
                .collect(Collectors.toList());

        ResponseDTO<KidCountDTO> response = ResponseDTO.<KidCountDTO>builder()
                .data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("thisweek/{word}")
    public ResponseEntity<?> getThisWeekNewsList(@PathVariable String word) {

        List<KidNewsEntity> entities = service.getThisWeekNews(word);

        List<KidNewsDTO> dtos = entities.stream()
                .map(KidNewsDTO::new)
                .collect(Collectors.toList());

        ResponseDTO<KidNewsDTO> response = ResponseDTO.<KidNewsDTO>builder()
                .data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("lastweek/{date}/{word}")
    public ResponseEntity<?> getLastWeekNewsList(
            @PathVariable String date, @PathVariable(required = false) String word) {

        List<KidNewsEntity> entities = service.getLastWeekNews(date, word);

        List<KidNewsDTO> dtos = entities.stream()
                .map(KidNewsDTO::new)
                .collect(Collectors.toList());

        ResponseDTO<KidNewsDTO> response = ResponseDTO.<KidNewsDTO>builder()
                .data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

}
