package com.spring.mvcproject.chap02_5.score.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// JSP 파일로 옮기는 역할만 하는 클래스
// DB 수정은 ApiController 클래스에서 진행
@Controller
public class ScorePageController {

    // 사용자가 아래 path를 입력하면,
    @GetMapping("/score/page")
    // 1) Spring 내부 메커니즘에 의해, 클라이언트가 요청 보낸 path를 가진 @Mapping 어노테이션을 스캔하여,
    // 2) 그 밑에 있는 함수를 호출해줌
    // 3) 그 다음 단계로
    //    - @responsbody가 붙은 경우 : http 응답 본문을 반환
    //    - 안 붙은 경우 : 리턴된 jsp 파일을 뷰 리졸버를 통해 매핑에서 화면에 렌더링 되도록 함
    public String scorePage() { // String 으로 받는 이유: 받아서 Spring 내부 메커니즘에 의하여 jsp 파일로 이동
        return "score/score-page";
    }
}
