package com.spring.mvcproject.board.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.mvcproject.board.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardListDto {

    private Long bno; // 원본 게시물 번호
    private String shortTitle; // 5글자 이상 줄임 처리된 제목
    private String shortContent; // 15자 이상 줄임 처리된 글 내용

    @JsonFormat(pattern = "yyyy년 MM월 dd일")
    private LocalDateTime date; // 포맷팅된 날짜문자열 (2024/12/20)
    private int view; // 조회 수
    private boolean newArticle; // 새 게시물(1시간 이내)인가?

    public BoardListDto(Board b) {
        this.bno = b.getId();
        this.shortTitle = b.getTitle().length() > 5 ? b.getTitle().substring(0, 5) + "..." : b.getTitle();
        this.shortContent = b.getContent().length() > 15 ? b.getContent().substring(0, 15) + "..." : b.getContent();
//        this.date = b.getRegDateTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.date = b.getRegDateTime();
        this.view = b.getViewCount();
        //     12:00           <          10:30 + 1 => 11:30
        this.newArticle = LocalDateTime.now().isBefore(b.getRegDateTime().plusHours(1));
    }
}