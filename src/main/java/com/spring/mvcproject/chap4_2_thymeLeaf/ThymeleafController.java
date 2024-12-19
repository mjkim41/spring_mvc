package com.spring.mvcproject.chap4_2_thymeLeaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    // [타임리프]
    //  - 설명 : 백엔드에서 렌더링할 때, html 코드를 좀 더 쉽게 쓸 수 있게 해주는 템플릿
    //  - 동적 웹페이지 코드 저장 위치 : src > resources > templates > 000.html
    @GetMapping("/chap4-2/pet")
    public String pet(Model model) { // Model 객체 : 컨트롤러에서 생성된 데이터를 뷰로 전달하는 역할
                                     // vanille 코드에서 HttpRequestServlet.setAttribute 와 같은 역할
        model.addAttribute("username", "꼬끼오"); // Mode.addAttribute() : Model에 저장된 데이터를 뷰 템플릿에 전달하는 역할
        return "pet"; // src > resrcoures > templetes > pet.html로 자동 이동
    }
}
