package com.spring.mvcproject.board.dto.request;
import com.spring.mvcproject.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

// 클라이언트가 성적정보를 등록할 때
// 필요한 정보만을 컴팩트하게 담고, 입력값을 검증하는 객체
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveDto {

    // 필수값 검증
    // @NotBlank : 공백, null 둘 다 아니면
    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    // @Size(min= 5) : 공백 포함  5자 이상 입력할 것
    @NotBlank(message = "내용을 입력하세요")
    @Size(min = 5, message = "내용을 5자 이상 입력하세요.")
    private String content;

    public static Board toEntity(BoardSaveDto dto) {
        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setViewCount(0);
        board.setRegDateTime(LocalDateTime.now());
        return board;
    }


}
