package com.spring.mvcproject.chap6_3_Exception.entity;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private String account; // 계정명
    private String password; // 비밀번호
    private String nickname; // 닉네임
}