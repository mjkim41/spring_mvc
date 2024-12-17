package com.spring.mvcproject.chap02_3.quiz.quiz6.entity;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString @EqualsAndHashCode
// ! spring 한테 객체 만들어달라고 안 하는 이유
//   - spring은 singleton으로 만들 때 보통 씀
public class Book1 {

    private Long id;
    private String title;
    private String author;
    private int price;

    public void updateBook(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

}
