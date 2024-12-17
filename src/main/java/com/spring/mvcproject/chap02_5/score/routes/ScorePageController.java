package com.spring.mvcproject.chap02_5.score.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// jsp 여는 것만 전담
@Controller
public class ScorePageController {

    // 사용자가 아래 path를 입력하면,
    @GetMapping("/score/page")
    // score/score-page.jsp 파일을 화면에 보여줘.
    public String scorePage() {
        return "score/score-page";
    }
}
