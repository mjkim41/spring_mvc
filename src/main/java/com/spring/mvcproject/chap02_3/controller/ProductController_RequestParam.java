package com.spring.mvcproject.chap02_3.controller;

import com.spring.mvcproject.chap02_3.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
public class ProductController_RequestParam {

    // 가상의 메모리 상품 저장소
    private Map<Long, Product> productStore = new HashMap<>();

    public ProductController_RequestParam() {
        productStore.put(1L, new Product(1L, "에어컨", 10000));
        productStore.put(2L, new Product(2L, "세탁기", 10500));
        productStore.put(3L, new Product(3L, "에어프라이어", 26000));
    }


    // 1. @GetMapping("/경로(path)/) : ()안에 있는 path에 대한 요청이 오면 이 메서드를 호출해 주세요.
    @GetMapping("/products2")
    public String getProduct(
            /*
               2. 생성자로 ' @RequestParam("스트링쿼리 키 네임") 데이터타입 변수명 ' 전달

                 - 의미 : 요청 경로의 스트링 쿼리(경로?id=1 에서 id=1 부분)을 가져와 주세요.
                 - 아래 코드와 동일한 효력임
                     public getProduct(HttpRequestServlet req) {
                        데이터타입 변수명 = req.getParameter(스트링쿼리Key네임)
                     }
                  - Option
                    i. @RequestParam(value="String Query Key Name", required=false, defaultValue="3")
                       - required=false면 값 안 넣어도 됨
                       - defaultValue = required=false일 때 입력안 할 시 이 값으로 자동으로 넣어줌
                    ii. String Query Key Value 값을  Key Name 과 동일한 이름으로 변수명을 받을 경우,
                        @RequestParam("keyname") 부분은 생략 가능
               */
           Long id,
           @RequestParam(required = false, defaultValue = "1000") int price // int 하면 오류 발생 방지

    ) {
        System.out.println("id = " + id);
        System.out.println("price = " + price);
        return "hello";
    }

}
