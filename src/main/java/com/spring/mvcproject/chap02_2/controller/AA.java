package com.spring.mvcproject.chap02_2.controller;

import org.springframework.stereotype.Controller;

/*
[@Controller 작동 원리]
1. 클라이언트가 http 요청을 보낸다.
2-1. Spring application을 시작하면 기본적으로 src>main>java에 기본적으로 저장되어 있는 java 파일이 실행되고,
   이에 의해 이 파일에 있는 SpringApplication.run(MvcprojectApplication.class, args);이라는 코드도 자동 실행된다.
2-2. 위의 run 메소드가 작동하면,
     내장된 TOMCAT 서버가 실행되면서
     Spring boot가 자동으로 DispatcherServlet을 등록한다.
     이 DispatcherServlet이 WebApplicationContext에 등록된다.
3. 클라이언트가 보낸 http 요청이 tomcat 서버에 의해 dispatcherServlet에 등록되고,
    DispatcherServlet은 적절한 컨트롤러를 찾아 요청을 처리하고, 결과를 클라이언트에게 반환


 */
@Controller
public class AA {
}
