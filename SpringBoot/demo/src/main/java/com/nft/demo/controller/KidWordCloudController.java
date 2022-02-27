package com.nft.demo.controller;

import com.nft.demo.dto.KidCountDTO;
import com.nft.demo.dto.KidNewsDTO;
import com.nft.demo.dto.ResponseDTO;
import com.nft.demo.model.KidCountEntity;
import com.nft.demo.model.KidNewsEntity;
import com.nft.demo.service.KidWordCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// http://localhost/kid/lastweek/2022-01-05
@CrossOrigin(origins = "http://localhost:3000") // React 연결
@RestController
@RequestMapping("kid")
public class KidWordCloudController {

    @Autowired //자바빈을 찾아서 인스턴스 멤버 변수에 연결
    private KidWordCloudService service;

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

    @GetMapping("lastweek/{date}")
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

    //    http://localhost/kid/lastweek/2022-01-05/호랑이
    @GetMapping("lastweek/{date}/{word}")
    public ResponseEntity<?> getLastWeekNewsList(
            @PathVariable String date, @PathVariable(required = true) String word) {

        List<KidNewsEntity> entities = service.getLastWeekNews(date, word);

        List<KidNewsDTO> dtos = entities.stream()
                .map(KidNewsDTO::new)
                .collect(Collectors.toList());

        ResponseDTO<KidNewsDTO> response = ResponseDTO.<KidNewsDTO>builder()
                .data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

}
