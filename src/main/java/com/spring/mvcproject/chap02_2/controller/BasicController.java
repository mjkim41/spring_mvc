package com.spring.mvcproject.chap02_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

// 자세한 설명운 chap02_3 controller의 ProductController참고
@Controller
public class BasicController {

    // 클라이어느가 get 요청 했을 때
    // 옛날 방식
    // @RequestMapping(value = "/chap2-2/hello", method = RequestMethod.GET
    @GetMapping("/chap2-2/hello")
    @ResponseBody // 데이터를 클라이언트에 응답
    public String hello() {
        System.out.println("GET 요청이 들어옴");
        return "hello spring";
    }

    // jsp를 응답
    // 1. build.gradle 에 추가
    @GetMapping("/chap2-2/getjsp")
//    @ResponseBody : jsp가 아닌 json 으로 응답
    public String getJsp() {
//        "/WEB-INF/views/hello.jsp";
        // application properties 수정
        return "hello";
    }


}
