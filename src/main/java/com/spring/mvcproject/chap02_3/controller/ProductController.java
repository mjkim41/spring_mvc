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

/*
 [Spring에서 클라이언트의 http 요청 다루기]
 - 이 파일에서는 클라이언트의 http 요청을 DispatcherServlet을 통해 받은 후,
   DispatcherServlet이 각 요청을 컨트롤러에 매핑해줄 수 있도록
    1) @Controller를 사용하여 Spring Application 실행 시 @cONTROLLER가 붙은 클래스에 대해 객체가 자동생성될 수 있도록 하고
    2) @GetMapping, @RequestMapping을 통해 '경로가 /000 이면 여기에 있는 메소드를 호출해주세요'라고 하는 것에 대해 정리함
 */

// @Controller :
//    - Spring application 실행하면 자동으로 src>main>java에 내장되어 있는 파일이 실행되면서,
//      SpringApplication.run() 코드 실행됨
//      -> 이에 따라 @Controller가 붙은 클래스가 자동으로 스캔되고,
//         WebApplicationContext(Spring에서 bean을 관리하고 초기화하는 인터페이스)에 등록 되어 자동으로 생성됨
@Controller
public class ProductController {

     // 가상의 메모리 상품 저장소
    private Map<Long, Product> productStore = new HashMap<>();

    public ProductController() {
        productStore.put(1L, new Product(1L, "에어컨", 10000));
        productStore.put(2L, new Product(2L, "세탁기", 10500));
        productStore.put(3L, new Product(3L, "에어프라이어", 26000));
    }

   //옛날 방식
    // DispatcherServlet아, url/products를 get 요청하면 이 메소드를 호출해줘
    @GetMapping("/products")
    // 근데 호출할 때, 클라이언트가 http 요청 주면 httpServletRequest가 생성되잖아
    // 걔도 그 메소드에서 필요하니까 좀 같이 보내줘
    public String getProduct(HttpServletRequest req) {
         //  HttpServletRequest인 req 객체에 스트킹 쿼리 값 요청
        String id = req.getParameter("id");
        String price = req.getParameter("price");
        System.out.println("/products?id=%s : GET 요청이 들어옴".formatted(id));
        System.out.println("id = " + id);
        System.out.println("price = " + price);
        return "ㅇ";
    }



}
