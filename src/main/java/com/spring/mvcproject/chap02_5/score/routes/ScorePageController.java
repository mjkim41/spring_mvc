package com.spring.mvcproject.chap02_5.score.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// jsp 여는 것만 전담
@Controller
public class ScorePageController {



    @GetMapping("/score/page")
    public String scorePage() {
        return "score/score-page";
    }
}
