package com.spring.mvcproject.chap6_3_Exception.exception;

import com.spring.mvcproject.chap6_3_Exception.exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

// API에서 발생하는 수많은 예외상황들을 도맡아서 처리하는 AOP클래스
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 예외처리 메서드
    @ExceptionHandler(MemberException.class)  // 받아서 처리할 예외의 이름
    public ResponseEntity<?> handleClientException(
            MemberException e
            , HttpServletRequest request
    ) {

        // 로깅 처리
        log.warn("exception occurred!! caused by: {}", e.getMessage());

        // 구체적인 에러 객체 생성
        ErrorResponse error = ErrorResponse.builder()
                .path(request.getRequestURI())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .status(e.getStatus().value())
                .error(e.getStatus().getReasonPhrase())
                .build();

        return ResponseEntity
                .status(error.getStatus())
                .body(error);
    }


    // 입력값 검증 관련 모든 예외처리 통합
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBindingError(
            MethodArgumentNotValidException e
            , HttpServletRequest request
    ) {

        String message = e.getBindingResult().getFieldError().getDefaultMessage();

        // 로깅 처리
        log.warn("input value exception occurred!! caused by: {}", message);

        // 구체적인 에러 객체 생성
        ErrorResponse error = ErrorResponse.builder()
                .path(request.getRequestURI())
                .message(message)
                .timestamp(LocalDateTime.now())
                .status(e.getStatusCode().value())
                .error(e.getStatusCode().toString())
                .build();

        return ResponseEntity
                .status(error.getStatus())
                .body(error);
    }


}