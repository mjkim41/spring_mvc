package com.spring.mvcproject.board.api;

import com.spring.mvcproject.board.dto.request.BoardSaveDto;
import com.spring.mvcproject.board.entity.Board;
import com.spring.mvcproject.score.entity.Score;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardApiController {

    private Map<Long, Board> boardStore = new HashMap<>();

    private long nextId = 1;

    public BoardApiController() {

        Board b1 = Board.of(nextId++, "꿀잼게시물", "개노잼이야 사실");
        Board b2 = Board.of(nextId++, "앙영하긔", "긔긔요미미미ㅣ");
        Board b3 = Board.of(nextId++, "이마트 갈때...", "홈플러스 쿠폰써도 되나요");

        boardStore.put(b1.getId(), b1);
        boardStore.put(b2.getId(), b2);
        boardStore.put(b3.getId(), b3);
    }

    // 게시물 목록조회 GET
    @GetMapping
    public List<Board> boardList() {
        // 게시물 목록은 최신글이 가장 위에 있어야 함
        return new ArrayList<>(boardStore.values())
                .stream()
                .sorted(comparing(Board::getRegDateTime).reversed())
                .collect(toList())
                ;
    }

    // 게시물 삭제 DELETE
    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Long id) {
        Board removed = boardStore.remove(id);
        return "게시물 삭제 성공! - " + removed;
    }

    // 게시물 등록 POST
    @PostMapping
    public ResponseEntity<?> createBoard(
            @RequestBody @Valid BoardSaveDto dto
            // 입력값 결과 검증 결과를 가진 객체. spring에서 자동 생성됨
            , BindingResult bindingResult
    ) {
        // 입력값 검증에서 에러가 발생했다면
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                errorMap.put(err.getField(), err.getDefaultMessage());
            });
            return ResponseEntity
                    .badRequest()
                    .body(errorMap);
        }
        // dto를 Board 객체로 변환 후 boardStore 해쉬맵에 추가
        Board board = new Board(dto);
        board.setRegDateTime(LocalDateTime.now());
        board.setId(nextId++);
        board.setViewCount(0);
        boardStore.put(board.getId(), board);

        // 클라이언트에 응답 전달
        return ResponseEntity
                .ok()
                .body("게시글 등록 완료" + board);
    }


}