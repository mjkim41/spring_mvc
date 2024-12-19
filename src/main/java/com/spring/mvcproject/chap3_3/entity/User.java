package com.spring.mvcproject.chap3_3.entity;

import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private int age;
}
