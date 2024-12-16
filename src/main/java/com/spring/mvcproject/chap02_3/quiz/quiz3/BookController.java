package com.spring.mvcproject.chap02_3.quiz.quiz3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    @GetMapping("/books")
    @ResponseBody
    public String getBooks(
        @RequestParam("author") String author,
        @RequestParam("genre") String genre
    ) {
        return "Author: " + author + ", Genre: " + genre;
    }
}
