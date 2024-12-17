package com.spring.mvcproject.chap02_3.controller;

import com.spring.mvcproject.chap02_3.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

// @Controller : spring application 실행 시 기본 파일의 SpringApplication.run 메소드가 실행되고,
//              이에 의해 @Controller 붙은 빈들이 Spring의 ApplicationContext에 등록되어 생성 및 초기화됨
//              클라이언트가 http 요청 시, 웹 서버 -> WAS -> DispatcherServlet로 요청 전달
//              -> DispatcherServlet를 WebApplicationContext를 사용하여 컨트롤러를 요청에 매핑함

@Controller
public class AZ {

    private Map<Long, Product> a = new HashMap<>();

    public AZ() {
        a.put(1L, new Product(1L, "에어컨", 10000));
        a.put(2L, new Product(2L, "세탁기", 10500));
    }

    @GetMapping("abc/{id1}/{id2}")
    @ResponseBody
    public String getA(
            @PathVariable String id1,
            @PathVariable String id2
    ) {
        return id1 + id2;
    }
}
