package com.spring.mvcproject.chap08.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

// 파일업로드 관련 설정 클래스
@Getter @Setter
@Configuration
public class FileUploadConfig {

    // application.yml에 있는 설정 값을 가져옴
    @Value("${file.upload.location}")
    private String location; // 파일을 저장할 루트 디렉토리

    // 서버가 실행되면 해당 디렉토리를 생성
    @PostConstruct   // 스프링이 실행되면 자동으로 실행할 코드
    public void init() {
        File directory = new File(location);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }






}
