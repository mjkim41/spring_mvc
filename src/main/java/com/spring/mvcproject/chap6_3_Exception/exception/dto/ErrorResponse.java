package com.spring.mvcproject.chap6_3_Exception.exception.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private LocalDateTime timestamp; // 에러가 발생한 시간
    private int status;  // 에러 상태코드
    private String error; // 에러의 이름
    private String message; // 에러의 구체적인 원인 메시지
    private String path; // 에러가 발생한 경로
}