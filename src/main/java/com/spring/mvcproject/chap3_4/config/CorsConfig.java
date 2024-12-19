package com.spring.mvcproject.chap3_4.config;

import com.spring.mvcproject.chap02_3.entity.Product;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// CORS 설정(Cross Origin)
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // 요청을 허용할 서버 URL
                .addMapping("/api/v1/boards")
                // 요청을 허용할 클라이언트 url
                .allowedOrigins("http://127.0.0.1:5500")
                // 요청을 허용할 요청 방식
                .allowedMethods("GET")
                .allowedHeaders("*");
    }
}
