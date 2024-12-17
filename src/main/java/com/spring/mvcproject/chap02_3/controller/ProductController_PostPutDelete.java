package com.spring.mvcproject.chap02_3.controller;

import com.spring.mvcproject.chap02_3.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController_PostPutDelete {

    // !!!  #여기부터#  <- 키워드로 검색하여 거기부터 보면 됨

    // 가상의 메모리 상품 저장소
    private Map<Long, Product> productStore = new HashMap<>();

    // 상품의 id를 자동 생성
    private long nextId = 1;

    public ProductController_PostPutDelete() {
        productStore.put(nextId, new Product(nextId, "에어컨", 1000000));
        nextId++;
        productStore.put(nextId, new Product(nextId, "세탁기", 1500000));
        nextId++;
        productStore.put(nextId, new Product(nextId, "공기청정기", 200000));
        nextId++;
    }

    @GetMapping("/{id}")
    @ResponseBody  // JSON 응답
    public Product getProduct(
            @PathVariable Long id
    ) {
        System.out.println("/products/%s  : GET 요청이 들어옴!".formatted(id));
        System.out.println("id = " + id);

        Product product = productStore.get(id);
        return product;
    }

    // ############ 여기부터 보면 됨 ########### //
    // === GetMapping을 이용한 전체 목록 조회 요청 처리=== //
    @GetMapping("/products")
    public List<Product> getList() {
        /* 전체 목록 보기 하는 법 : (HashMap) productStore을 가져와서, key 값만 추출해서, List로 만들기 */
        // 방법 아래 2개 있음
        //  - 방법 1. ArrayList이 생성자로 collection을 받으므로, hashMap.value()로 hashMap을 colleciton으로 변환환 후
        //           ArrayList로 객체생성
        ArrayList<Product> productList = new ArrayList<>(productStore.values());
        return productList;

        /*

        //  - 방법2. hashmap의 values만 봅은 다움, stream().collecto(로 List로 변환)
        /*  return productStore.values()
                .stream()
                .collect(Collectors.toList());
         */
    }

    // ==== PostMapping을 이용한 (hASHmAP)ProductStore에 새 제품 추가 ==== //
    // @PostMapping : 과거 방식은 @RequestMapping(value="", method=RequestMethod.DELETE)
    @PostMapping // @PostMapping에 괄호 생략 or ("")로 적어주면 : 그 주소 자체에
    /* 사용자가 url에 /test?id=1&name=에어컨 이런 식으로 적어주면
       -> 서버는 스트링 쿼리 값을 가져와서
       -> 일단 product를 만들어 주고
       -> 그 product를 productStore map에 추가
     */
    //  1. /test?id=1&name=미돌 이런 식으로 전달해주면
    public String test(
            // 2. 스트링 쿼리에서 값을 가져와서
            @RequestParam(value="price", required = false, defaultValue = "1000") int price,
            @RequestParam(value="name", required = false, defaultValue = "하하") String name
    ) {
    // 3. 스트링 쿼리 값을 바탕으로 Product 객체를 생성해주고
        Product newProduct = new Product(nextId++, name, price);
        productStore.put(newProduct.getId(), newProduct);
        return "제품에 추가 되었습니다." + newProduct;
    }
    // 4. 방금 생성한 Product 객체를 productStore 맵에 추가해주자.


    // ====== PutMapping을 이용한 제품 정보 수정 ======= //
    @PutMapping("/{id}")
     /* 사용자가 url에 /products/1?price=2000 이런 식으로 적어주면
       -> 서버는 스트링 쿼리 값을 가져와서
       -> Product을 찾아서 Product의 정보를 수정한다.
       -> productStore 맵에서 get(key name)을 통해서

     */
    public String modifyProduct(
            // 1. 사용자가 url에 /products/1?price=2000 이런 식으로 적어주면
            //     서버는 스트링 쿼리 값을 가져와서
            @PathVariable("id") Long id,
            @RequestParam(value="price", required = false, defaultValue = "0") int newPrice,
            @RequestParam(value="name", required = false) String newName
    ) {
        // 스트링 쿼리의 값을 바탕으로 productStore Map에서 해당 Product를 찾은 후
        Product foundProduct = productStore.get(id);
        // 해당 Product의 값을 수정해준다.
        if (newPrice != 0) {
            foundProduct.setPrice(newPrice);
        }
        if (newName != null) {
            foundProduct.setName(newName);
        }

        return "상품이 수정되었습니다. - " + foundProduct;
    }

        // 상품 삭제 요청
        @DeleteMapping("/{id}")
        public String deleteProduct(
                @PathVariable Long id
    ) {
            productStore.remove(id);
            return id + "번 상품이 삭제되었습니다.";
        }

    }