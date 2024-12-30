package com.spring.mvcproject.chap6_3_Exception.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberException extends RuntimeException {

    private HttpStatus status; // 에러 상태코드

    public MemberException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}