package com.spring.mvcproject.chap02_3.controller;

import com.spring.mvcproject.chap02_3.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products3")
public class ProductController_getParameter {

    // 가상의 메모리 상품 저장소
    private Map<Long, Product> productStore = new HashMap<>();

    // 상품의 id 자동 생성
    private Long nextId = 1L;

    // 생성자는 spring application이 실행될 때 자동 생성됨
    public ProductController_getParameter() {
        // nextId++ : 일단 이번꺼에는 이미 저장되어 있는 값으로 쓰고, 그 다음에 값을 1을 올리세요.
        productStore.put(nextId++, new Product(nextId++, "에어컨", 10000));
        productStore.put(nextId++, new Product(nextId++, "냉장고", 20000));
        productStore.put(nextId++, new Product(nextId++, "냉장고", 20000));
    }

    // 특정 상품 조회
    /* 옛날 버전
      - DispatcherServlet 에게서 HttlServletRequest req 을 전달받아,
        getPraemter를 통해 스트링 쿼리의 값을 가져옴
     */
    @GetMapping("/products3")
    public String getProduct(HttpServletRequest req) {
        String id = req.getParameter("id");

        return "/products?id=%s : GET 요청이 들어옴".formatted(id);
    }

}