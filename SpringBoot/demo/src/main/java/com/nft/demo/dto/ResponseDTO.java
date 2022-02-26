package com.nft.demo.dto;
/*
 * @Date : 2022.02.26
 * @version : 1.0
 * @Description : HTTP 응답으로 사용할 DTO
 */

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {
    private String error;
    private List<T> data;
}
