package com.spring.mvcproject.board.dto.response;

import com.spring.mvcproject.board.entity.Board;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BoardListDtoTest {

    @Test
    void test() {
        // 원본 게시물
        Board b = new Board(1L, "히히히히히히", "내용을 적는 중입니다.", 0, LocalDateTime.of(2024, 12, 20, 11, 45));
        BoardListDto dto = new BoardListDto(b);
        System.out.println("dto = " + dto);
    }

}