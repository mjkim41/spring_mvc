package com.spring.mvcproject.chap02_5.score.entity;

import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    // 이 객체의 id
    private Long id;
    // 1, 2, 3, 4 이런 식으로 id를 올리기 위해서는,
    // 이 클래스의 모든 객체에서 대해서 공통으로 공유하는 id 값이 있어야 함
    private static Long idCounter = 0L;
    private String name;
    private int kor, eng, math;


    public Score(String name, int kor, int eng, int math) {
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        this.id= idCounter++;
    }


}
