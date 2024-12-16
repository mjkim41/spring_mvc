package com.spring.mvcproject.chap02_3.quiz.quiz4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    @GetMapping("/search")
    @ResponseBody
    public String search(
            @RequestParam("query") String query,
            @RequestParam(value = "page", defaultValue = "1") int page
            ) {
        return "Query: " + query + ", Page: " + page;
    }

}
