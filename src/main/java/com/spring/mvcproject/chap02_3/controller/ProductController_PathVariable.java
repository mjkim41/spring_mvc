package com.spring.mvcproject.chap02_3.controller;

import com.spring.mvcproject.chap02_3.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ProductController_PathVariable {



    // 기본 세팅 : 가상의 메모리 상품 저장소
    private Map<Long, Product> productStore = new HashMap<>();

    // 기본 세팅
    public ProductController_PathVariable() {
        productStore.put(1L, new Product(1L, "에어컨", 10000));
        productStore.put(2L, new Product(2L, "세탁기", 10500));
        productStore.put(3L, new Product(3L, "에어프라이어", 26000));
    }

    // 1. @GetMapping("/경로(path)/{경로변수}) : ()안에 있는 path에 대한 요청이 오면 이 메서드를 호출해 주세요.
    @GetMapping("/products1/{id}")
    // @ResponseBody : 메서드가 반환하는 값을 뷰(view)로 전달하지 말고, http 응답으로 바로 전달해주세요.
    //                  -> 개발자도구에서 network의 response에 보이고, 화면에도 렌더링 됨
    //                 @ResponseBody를 쓰지 않으면, Spring에서는 WebApp에서 retur에 있는 파일을 찾으러 감
    @ResponseBody
    public Product getProduct(
            /*
               @PathVariable("id") Long id
                 - 아래 코드와 동일한 효력(HttpRequestServlet을 생성자로 전달해 준 후, HttpRequestServlet에서 parameter값을 가져오는 것)

                 -   public getProduct(HttpRequestServlet req) {
                        String productId = extractPathVariable(request, "id");
                      }
             */
            @PathVariable("id") Long id // 변수명과 변수경로가 같을 경우 생략 가능(@PathVariable Long id)
    ) {
        System.out.println("id = " + id);
        Product product = productStore.get(id);
        return product;
    }


}
