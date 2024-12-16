package com.spring.mvcproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvcprojectApplication {

	public static void main(String[] args) {
		// Spring Application 실행 시 이 파일이 가장 먼저 실행 되면서
		// 아래 SpringApplication.run() 코드가 실행 됨
		// -> 이에 의해 @Controller가 붙은 클래스를 자동으로 스캔하여 dispatcherServlet에 등록되고
		// -> DispatcherServlet에 등록된 객체가 WebApplicationContext에 등록되면서 빈이 생성 및 초기화됨
		SpringApplication.run(MvcprojectApplication.class, args);
	}

}
