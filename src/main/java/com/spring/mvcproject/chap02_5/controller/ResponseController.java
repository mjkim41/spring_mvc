package com.spring.mvcproject.chap02_5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ResponseController {

    // 페이지 라우팅 - 특정 뷰(JSP, Thymeleaf)를 포워딩(열어주는 것)
    @GetMapping("/pet")
    public String pet() {
        return "pet";
    }

    // html 응답
    @GetMapping("/show/html")
    @ResponseBody // 페이지 라우팅을 하지 않고, 순수 리던데이터를 클라이언트에 전송
    // json 응답 뺴고는 다 @responsebody 붙여야 함
    public String html() {
        return """
                <html>
                <body>
                  <h1>메롱메롱 html</h1>
                </body>
                </html>
                """;
    }

    @GetMapping("/show/text")
    @ResponseBody
    public String text() {
        return "메롱메롱 html";
    }

    // json 배열 응답 - 자바의 배열이나 리스트를 리턴하면 json 배열로 응답됨
    @GetMapping("/json/hobbies")
    @ResponseBody
    public List<String> hobbies() {
        return List.of("수영", "크로스핏", "스트레칭");
    }

    @GetMapping("/json/hobby")
    @ResponseBody
    public String hobby() {
        return "운동";
    }

    // json 객체 응답 - 자바의 Map이나 클래스의 객체를 리턴
    @GetMapping("/json/my-cat")
    @ResponseBody
    public Map<String, Object> myCat() {
        return Map.of(
                "name", "냐옹이",
                "age", 3,
                "injection", false
        );
    }


    @GetMapping("/json/my-cat2")
    @ResponseBody
    public Cat myCat2() {
        return new Cat("야옹양옹", 5, true);
    }


    // JSON 객체 배열
    @GetMapping("/json/my-cats")
    @ResponseBody
    public List<Cat> cats() {
        return List.of(
                new Cat("옹냥이", 3, false),
                new Cat("옹늉이", 7, true)
        );
    }


}


