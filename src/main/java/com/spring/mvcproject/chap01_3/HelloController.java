package com.spring.mvcproject.chap1_3;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/* dispatcher servlet의 역할 :
 1. @Controller 를 탐색해서 bean을 생성
 2. @RequuestMapping은 여기러 처리(@webservlet 역할)
 3. 그 안의 메소드 자동 실행

*/
// Controller: 클라이언트의 요청을 받아 로직을 수행하는 역할
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
    public String bye() {
        System.out.println("bye~~ spring mvc world!");
        return "";
    }
}
