package com.spring.mvcproject.database.mybatis.entity;

/*
USE practice;
CREATE TABLE tbl_pet (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pet_name VARCHAR(50),
    pet_age INT,
    injection BOOLEAN
);

 */

import lombok.*;

@Setter @Getter  @ToString
//
@EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor
//
@Builder

public class Pet {


    private Long id;
    private String petName;
    private int petAge;
    private boolean injection;
}
