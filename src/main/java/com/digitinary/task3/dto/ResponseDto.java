package com.digitinary.task3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ResponseDto {
    private String statusCode;

    private String statusMsg;


}