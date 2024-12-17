package com.spring.mvcproject.chap02_5.score.entity;

import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    private Long id; // 학번
    private String name;
    private int kor, eng, math; // 국영수 점수

}
