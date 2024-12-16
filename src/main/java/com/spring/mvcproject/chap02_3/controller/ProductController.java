package com.spring.mvcproject.chap02_3.controller;

import com.spring.mvcproject.chap02_3.entitity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {

    // 가상의 메모리 상품 저장소
    private Map<Long, Product> productStore = new HashMap<>();

    public ProductController() {
        productStore.put(1L, new Product(1L, "에어컨", 10000));
        productStore.put(2L, new Product(2L, "세탁기", 10500));
        productStore.put(3L, new Product(3L, "에어프라이어", 26000));
    }

     // 옛날 방식
//    // 상품 조회 : GET
//    @GetMapping("/products")
//    // 파라미터로 가져와서
//    public String getProduct(HttpServletRequest req) {
//        String id = req.getParameter("id");
//        String price = req.getParameter("price");
//        System.out.println("/products?id=%s : GET 요청이 들어옴".formatted(id));
//        System.out.println("id = " + id);
//        System.out.println("price = " + price);
//        return "ㅇ";
//    }


    // 2번 방식
//    // 상품 조회 : GET
//    @GetMapping("/products")
//    // 파라미터로 가져와서
//    public String getProduct(
//            // httpServletRequest을 생성자로 받아서,get.Paramete해서, Long id 로 받음
//            @RequestParam("id") Long id,
//            // = Long id
//            @RequestParam("price") int price
//
//    ) {
//        System.out.println("id = " + id);
//        System.out.println("price = " + price);
//        return "ㅇ";
//    }

//    // 상품 조회 : GET
//    @GetMapping("/products")
//    // 파라미터로 가져와서
//    public String getProduct(
//           Long id,
//           @RequestParam(required = false, defaultValue = "1000") int price // int 하면 오류 발생 방지
//
//    ) {
//        System.out.println("id = " + id);
//        System.out.println("price = " + price);
//        return "ㅇ";
//    }

    // 상품 조회 : GET
    @GetMapping("/products/{id}")
    // 파라미터로 가져와서
    @ResponseBody
    public Product getProduct(
            // @RequestParam("id") Long id
            // @Request Long id . value 속성 생략 (파라미터 이름과 메서드 파라미터 이름이 동일할 때):
            // 모든 속성 생략 (@RequestParam 생략, 파라미터 이름이 동일할 때):

            // 경로에서 읽겠따
//            @PathVariable("id") Long id
            @PathVariable Long id
    ) {
        System.out.println("id = " + id);

        Product product = productStore.get(id);

        return product;
    }



}
