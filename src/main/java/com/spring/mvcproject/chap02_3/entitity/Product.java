package com.spring.mvcproject.chap02_3.entitity;

import lombok.*;



@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private int price;
}
