package com.spring.mvcproject.chap4_2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    // 뷰(JSP, Thyeleaf 등) 포워딩
    @GetMapping("/chap4-2/pet")
    // Model 객체 역할 : 데이터 전달: 컨트롤러에서 생성된 데이터를 뷰로 전달합니다.
    // -> 뷰 템플릿에서 Model에 저장된 데이터를 사용하여 동적으로 콘텐츠를 생성합니다.
    public String pet(Model model) {
        //Thyemeleaf 기본 경로 : /templetes
        model.addAttribute("username", "꼬끼오");
        return "pet";
    }
}
