package com.spring.mvcproject.chap6_3_Exception.api;

import com.spring.mvcproject.chap6_3_Exception.dto.request.MemberCreateRequest;
import com.spring.mvcproject.chap6_3_Exception.entity.Member;
import com.spring.mvcproject.chap6_3_Exception.exception.MemberException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v6/members")
@Slf4j  // 로그 라이브러리
public class MemberApiController {

    private Map<String, Member> memberStore = new HashMap<>();

    public MemberApiController() {
        Member m1 = Member.builder()
                .account("abc1234")
                .password("12345678")
                .nickname("호돌이")
                .build();

        Member m2 = Member.builder()
                .account("def9876")
                .password("12345678")
                .nickname("핑돌이")
                .build();

        memberStore.put(m1.getAccount(), m1);
        memberStore.put(m2.getAccount(), m2);
    }

    // 회원 단일 조회
    @GetMapping("/{account}")
    public ResponseEntity<?> findOne(@PathVariable String account) {

        log.info("회원 단일 조회 요청이 들어옴!");
        log.debug("parameter account : {}", account);

        if (account.length() > 10) {
//            log.warn("사용자 입력값 오류!!");
            throw new MemberException(HttpStatus.BAD_REQUEST, "계정명은 10글자 이내여야 합니다.");
        }

        Member member = memberStore.get(account);

        if (member == null) {
//            log.error("심각한 오류 발생!");
            throw new MemberException(HttpStatus.NOT_FOUND, "회원 계정명을 다시 확인하세요!");
        }

        return ResponseEntity.ok().body(member);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody @Valid MemberCreateRequest dto
    ) {

        Member member = Member.builder()
                .nickname(dto.getNickname())
                .account(dto.getAccount())
                .password(dto.getPassword())
                .build();

        memberStore.put(member.getAccount(), member);

        return ResponseEntity.ok().body(member);
    }

}