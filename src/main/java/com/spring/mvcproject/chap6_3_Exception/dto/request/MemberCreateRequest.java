package com.spring.mvcproject.chap6_3_Exception.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateRequest {

    @Email(message = "계정명은 이메일형태로 써주세요.")
    @NotBlank(message = "계정명은 필수입니다.")
    private String account;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "8자리 이상으로 작성하세요.")
    private String password;

    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;
}