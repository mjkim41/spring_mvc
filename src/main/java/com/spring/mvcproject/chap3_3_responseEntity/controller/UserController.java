package com.spring.mvcproject.chap3_3_responseEntity.controller;

import com.spring.mvcproject.chap3_3_responseEntity.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// @ResponseBody : Mapping이 되면 Mapping에 있는 메소드가 호출되며 ResponseEntity 객체가 반환 되는데,
//                이 ResponseEntity의 Body를 클라이언트와 통신하기 위해 필요한 데이터 형식인 JSON으로 변환시켜 줌 (spring 내부 원리에 의해)
@RestController
@RequestMapping("/api/v3-3/users")
public class UserController {

    private Map<Long, User> userStore = new HashMap<>();
    private long nextId = 1;

    // 사용자 생성
    @PostMapping
    // @ResponBody인 경우, 작동 원리가
    //     1. @Mapping 밑에 있는 메소드가 호출 되는데,
    //        이 때 메소드의 반환 데이터는 ResponseEntity<?> 데이터 타입이여야 함.
    //     2. 그럼 이 ResponseEntity을 반환 받아서 Spring 내부 원리에 의하여
    //        ResponseEntity의 body는 클라이언트와 통신하기 위해 json 형태로 변환됨
    //     3. 이 때, ResponseEntity 객체는 아래와 같이 정보를 넣을 수 있음
    //         ---status --
    //           1) ResponseEntity.status(HttpStatus.BAD_REQUEST) // spring 전용
    //           2) ResponseEntity.status(401) // spring 전용
    //           3) ResponseEntity.badRequest() // spring 전용
    //           4) Spring 미사용 시 : @WebServlet, response.setStatus(HttpServletResponse.SC_BAD_REQUEST) 사용
    //         ---- header ----
    //           1) ResponseEntity.header(headerName, headerValue) // spring 전용
    //           2) HttpHeaders headers = new HttpHeaders(); // spring 전용
    //               hedaers.add(headerName, headerValue)
    //           3) spring 안 쓸 때 : @WebServlet, response.setHeader() 사용
    //         ---- body ---
    //           1) ResponseEntity.body() // spirng 전용
    //           2) spring 안쓸 때 : @WebServlet, response.getWriter().write() 사용

    public ResponseEntity<String> createUser(@RequestBody User user) {
        // 검증
        if (user.getAge() < 0) {
            return ResponseEntity
                    .badRequest()
                    .header("cause", "incorrect-age")
                    .body("마이너스 나이가 어딨냐. age - " + user.getAge());
        }
        // ! isBlank()
        if (user.getName().isBlank()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("random", "random-answer");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("이름은 필수값입니다~");
        }
        user.setId(nextId++);
        userStore.put(user.getId(), user);
        return ResponseEntity
                .status(200)
                .body("유저 정보가 생성되었습니다. -" + user);
    }
}
