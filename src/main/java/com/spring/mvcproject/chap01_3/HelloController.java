package com.spring.mvcproject.chap01_3;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 자세한 설명은 chap02_3 > controller > product controller
@Controller  // DispatcherServlet이 이 객체를 탐색해서 연결해줌
@RequestMapping("/chap01")
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody   // JSON 응답
    public String hello() {
        System.out.println("hello~~ spring mvc world!");
        return "메롱메롱 안녕안녕";
    }
    @RequestMapping("/bye")
    @ResponseBody
    public String bye() {
        System.out.println("bye~~ spring mvc world!");
        return "";
    }
}
