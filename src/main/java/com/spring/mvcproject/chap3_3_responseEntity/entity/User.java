package com.spring.mvcproject.chap3_3_responseEntity.entity;

import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private int age;
}
