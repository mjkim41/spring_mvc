package com.spring.mvcproject.chap02_3.quiz.quiz5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @GetMapping("/info/{userId}")
    public String getInfo(@PathVariable("userId") int userId) {
        return "User Info: " + userId;
    }
}
