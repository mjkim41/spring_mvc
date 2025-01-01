package com.spring.mvcproject.chap08.config;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 사용자가 업로드한 로컬에 저장된 파일에 접근하는 설정
@Configuration
@RequiredArgsConstructor
public class WebResourceConfig implements WebMvcConfigurer {

    private final FileUploadConfig fileUploadConfig;

    // 리소스 url 매핑

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // http://localhost:9000/uuploaders/loopy.jpg로 요청하면
        // 실제 로컬에 저장된 C:Users/user/spring/upload/loopy.jpg를 꺼내 주겠다.
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + fileUploadConfig.getLocation());
    }
}


