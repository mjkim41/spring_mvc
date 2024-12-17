package com.spring.mvcproject.chap02_5.controller;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cat {
    private String name;
    private int age;
    private boolean injection;
}
