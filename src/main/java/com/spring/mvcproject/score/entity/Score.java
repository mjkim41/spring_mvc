package com.spring.mvcproject.score.entity;

import com.spring.mvcproject.score.dto.request.ScoreCreateDto;
import lombok.*;

// 학생 한명의 성적정보를 저장
@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private Long id; // 학번
    private String name; // 이름;
    private int kor, eng, math; // 국영수 점수

    public Score(ScoreCreateDto dto) {
        this.name = dto.getStudentName();
        this.kor = dto.getKorean();
        this.eng = dto.getEnglish();
        this.math = dto.getMath();
    }
}