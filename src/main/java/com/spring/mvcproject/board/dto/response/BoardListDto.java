package com.spring.mvcproject.board.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.mvcproject.board.entity.Board;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Getter
@ToString
public class BoardListDto {

    private Long bno; // 원본 게시물 번호
    private String shortTitle; // 5글자 이상 줄임 처리된 제목
    private String shortContent; // 15자 이상 줄임 처리된 글 내용

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date; // 포맷팅된 날짜문자열)(YYY-MM-DD)

    private int view; // 조회 수
    private boolean newArticle;// 새 게시물(1시간 이내)인가?


    public BoardListDto(Board board) {
        this.bno = board.getId();
        this.shortTitle = createShortText(board.getTitle(), 3);
        this.shortContent = createShortText(board.getTitle(), 5);
        this.date = board.getRegDateTime();
        this.view = board.getViewCount();
        this.newArticle = isNewArticle(board);
    }

    // 내용 일부만 보이게 해주는 메소드
    private String createShortText(String originalText, int visibleLength) {
        //  1) 글자수가 # 이하면, 전체 추출
        if(originalText.length() <= visibleLength) {
            return originalText;
            // 2) 제목에 #글자 초과하면,
        } else {
            // 일단 #글자까지는 내용 추출하고
            String shortText = originalText.substring(0, visibleLength+1);
            // #+1번째글자(index: #) ~ 마지막 글자는 ***으로
            for (int i = visibleLength; i < originalText.length()-1; i++) {
                shortText += ".";
            }
            return shortText;
        }
    }


//    private String createShortDate(LocalDateTime regDateTime) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        return formatter.format(regDateTime);
//    }

    public boolean isNewArticle(Board board) {
        // 지난 시간 계산(ChronoUnit.MINUTES.between)
        long hoursPassed = ChronoUnit.MINUTES.between(LocalDateTime.now(), board.getRegDateTime());
        //
        return hoursPassed <= 60;
        // 다른방법 :
        // this.newArticle = LocalDateTime.now().isBefore(b.getRegDateTime().plusHours(1));
    }
}
