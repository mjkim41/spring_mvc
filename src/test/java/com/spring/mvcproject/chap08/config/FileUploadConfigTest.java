package com.spring.mvcproject.chap08.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileUploadConfigTest {

    @Autowired
    private FileUploadConfig config;

    @Test
    void test() {
        System.out.println(config.getLocation());
    }
}