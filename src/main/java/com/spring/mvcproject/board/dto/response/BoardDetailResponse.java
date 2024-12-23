package com.spring.mvcproject.board.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.mvcproject.board.entity.Board;
import lombok.Getter;
import lombok.ToString;


import java.time.LocalDateTime;

@ToString @Getter
public class BoardDetailResponse {

    private Long id; // 글번호
    private String title; //제목
    private String content; //내용

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date; //작성일시

    // 정적 팩토리 메서드
    public static BoardDetailResponse from(Board board) {
        BoardDetailResponse response = new BoardDetailResponse();
        response.id = board.getId();
        response.title = board.getTitle();
        response.content = board.getContent();
        response.date = board.getRegDateTime();
        return response;
    }
}