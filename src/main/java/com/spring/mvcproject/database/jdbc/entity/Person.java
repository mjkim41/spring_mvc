package com.spring.mvcproject.database.jdbc.entity;

// Entity(Domain) 클래스
// : DB 테이블과 1:!로 매칭되는 자바의 클래스


/*
CREATE TABLE tbl_person (
    -- BIGINT : Java의 Long 타입
	id BIGINT PRIMARY KEY,
    -- Oracle SQL : VARCHAR2
    person_name VARCHAR(100) NOT NULL,
    -- Oracle SQL : NUMBER()
	age INT DEFAULT 0
);
 */

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
public class Person {

    private Long id;
    // Java 관례에 맞춰서 person_name -> personName
    private String personName;
    private int age;
}
