package com.spring.mvcproject.board.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.mvcproject.board.entity.Board;
import lombok.Getter;
import lombok.ToString;


import java.time.LocalDateTime;

@ToString @Getter
public class BoardDetailDto {

    private Long id; // 글번호
    private String title; //제목
    private String content; //내용

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime date; //작성일시

    public BoardDetailDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.date = board.getRegDateTime();
    }
}
