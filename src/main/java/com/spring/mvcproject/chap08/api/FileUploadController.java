package com.spring.mvcproject.chap08.api;

import com.spring.mvcproject.chap08.config.FileUploadConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v8")
@Slf4j  // 로깅
@RequiredArgsConstructor
public class FileUploadController {

    // location 사용하기 위해 객체 가져옴
    private final FileUploadConfig fileUploadConfig;

    // ===============  단일 첨부파일 업로드 ==============
    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadSingleFile(
            // 일반적으로 @RequestParam은 쿼리 파라미터를 받지만, post file인경우에는 클라이언트가 보낸 json의 body의 해당 키 값을 가진 것의 value를 가져옴
            @RequestParam("profile") MultipartFile file
    ) {
        // MultiPartFile 내부 내장 메소드
        // - MultipartFile.getOriginalFilename(), MultipartFile.getContentType()
        log.info("uploaded file name: {}", file.getOriginalFilename());
        log.info("uploaded file type: {}", file.getContentType());

        // 저장 파일 경로 생성
        String rootDir = fileUploadConfig.getLocation();
        // 이름 중복 막기 위해 UUID.randomUUID
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        //   Users/user/spring/upload/티니핑.jpg
        // 경로 설정
        File uploadedLocation = new File(rootDir + fileName);

        // 해당 위치에 파일 저장
        try {
            // MultipartFile.transferTo(업로드할 경로)
            file.transferTo(uploadedLocation);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("파일 저장 실패!");
        }

        return ResponseEntity.ok().body("OK!");
    }




    // ===============  다중 첨부파일 업로드 ==============
    @PostMapping("/upload-multi")
    public ResponseEntity<?> uploadMultiFile(
            // 1. 클라이언트가 File을 POST하는 경우, (평상시네는 @RequestParam은 쿼리 파라미터를 받지만)
            //   @RequestParam으로 클라리언트가 Json Body에 보낸 File을 받음
            // 2. 이것을 아래에서 Spring framework에서 제공하는 MultipartFile이라는 객체로 받음
            // 3. MultipartFile.transferTo(파일 업로드 위치) 로 파일을 전송할 수 있음
            @RequestParam("profile") List<MultipartFile> fileList
            ) {
        for (MultipartFile file : fileList) {
            log.info("uploaded file name: {}", file.getOriginalFilename());
            log.info("uploaded file type: {}", file.getContentType());

            // 저장 파일 경로 생성
            String rootDir = fileUploadConfig.getLocation();
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File uploadedLocation = new File(rootDir + fileName);

            // 파일 저장
            try {
                // MultipartFile.transferTo(업로드할 경로)
                file.transferTo(uploadedLocation);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().body("파일 저장 실패!");
            }
        }
        return ResponseEntity.ok().body("multiple upload success!");

    }
}
