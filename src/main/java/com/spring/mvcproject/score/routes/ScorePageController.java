package com.spring.mvcproject.score.routes;

import org.mariadb.jdbc.Driver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// 성적관리에 필요한 JSP파일들을 포워딩하는 컨트롤러
@Controller
public class ScorePageController {

//    @GetMapping("/score/page")
//    public String scorePage(Model model) {
//        // 해당 JSP파일의 경로를 적음
//        model.addAttribute("title", "성적 관리");
//        model.addAttribute("foods", List.of("짜장", "떡볶이", "오렌지"));
//        return "score/score-page";
//
//    }

    @GetMapping("/score/page")
    public ModelAndView scorePage() {

        ModelAndView mv = new ModelAndView();
        // 해당 JSP파일의 경로를 적음
        mv.addObject("title", "성적 관리");
        mv.addObject("foods", List.of("짜장", "떡볶이", "오렌지"));

        mv.setViewName("score/score-page");
        return mv;
    }

    // 상세조회 페이지 라우팅
    @GetMapping("/score/{id}")
    public String detailPage(@PathVariable Long id, Model model) {
        System.out.println("/score/%s : GET".formatted(id));
        model.addAttribute("id", id);
        return "score/score-detail";
    }
}