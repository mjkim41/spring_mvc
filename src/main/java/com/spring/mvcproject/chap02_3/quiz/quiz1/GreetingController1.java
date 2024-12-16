package com.spring.mvcproject.chap02_3.quiz.quiz1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController1 {

    @GetMapping("/welcome")
    @ResponseBody // ResponseBody 안 쓰면 JSP 파일을 불러옴
    public String welcome() {
        return "Welcome to Spring MVC!";
    }
}
