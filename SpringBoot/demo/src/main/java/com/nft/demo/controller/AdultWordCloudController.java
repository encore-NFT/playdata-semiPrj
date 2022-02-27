package com.nft.demo.controller;

import com.nft.demo.dto.AdultCountDTO;
import com.nft.demo.dto.AdultNewsDTO;
import com.nft.demo.dto.ResponseDTO;
import com.nft.demo.model.AdultCountEntity;
import com.nft.demo.model.AdultNewsEntity;
import com.nft.demo.service.AdultWordCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// http://localhost/adult/lastweek/2022-01-05
@CrossOrigin(origins = "http://localhost:3000") // React 연결
@RestController
@RequestMapping("adult")
public class AdultWordCloudController {

    @Autowired //자바빈을 찾아서 인스턴스 멤버 변수에 연결
    private AdultWordCloudService service;

    @GetMapping("/thisweek")
    public ResponseEntity<?> getThisWeekWC() {
        List<AdultCountEntity> entities = service.getThisWeek();

        List<AdultCountDTO> dtos = entities.stream()
                .map(AdultCountDTO::new)
                .collect(Collectors.toList());

        ResponseDTO<AdultCountDTO> response = ResponseDTO.<AdultCountDTO>builder()
                .data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("lastweek/{date}") // @PathVariable : URI의 경로로 넘어오는 값을 변수로 받을 수 있다.
    public ResponseEntity<?> getLastWeekWC(@PathVariable(required = false) String date) {
        List<AdultCountEntity> entities = service.getLastWeek(date);

        List<AdultCountDTO> dtos = entities.stream()
                .map(AdultCountDTO::new)
                .collect(Collectors.toList());

        ResponseDTO<AdultCountDTO> response = ResponseDTO.<AdultCountDTO>builder()
                .data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("thisweek/{word}")
    public ResponseEntity<?> getThisWeekNewsList(@PathVariable String word) {

        List<AdultNewsEntity> entities = service.getThisWeekNews(word);

        List<AdultNewsDTO> dtos = entities.stream()
                .map(AdultNewsDTO::new)
                .collect(Collectors.toList());

        ResponseDTO<AdultNewsDTO> response = ResponseDTO.<AdultNewsDTO>builder()
                .data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("lastweek/{date}/{word}")
    public ResponseEntity<?> getLastWeekNewsList(
            @PathVariable String date, @PathVariable(required = false) String word) {

        List<AdultNewsEntity> entities = service.getLastWeekNews(date, word);

        List<AdultNewsDTO> dtos = entities.stream()
                .map(AdultNewsDTO::new)
                .collect(Collectors.toList());

        ResponseDTO<AdultNewsDTO> response = ResponseDTO.<AdultNewsDTO>builder()
                .data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

}
