package com.spring.mvcproject.chap02_3.quiz.quiz2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // @Controller + @ResponseBody 기능 합친 것
public class ProductController1 {

    @GetMapping("/product/{id}")
    @ResponseBody
    public String getProduct(@PathVariable("id") String productId) {
        return "Product ID: {" + productId + "}";
    }
}
