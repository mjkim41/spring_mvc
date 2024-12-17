package com.spring.mvcproject.chap02_5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResponseController {

    // 페이지 라우팅 - 특정 뷰(JSP, Thymeleaf)를 포워딩(열어주는 것)
    @GetMapping("/pet")
    public String pet() {
        return "pet";
    }
}
