package com.spring.mvcproject.score.api;

import com.spring.mvcproject.score.dto.request.ScoreCreateDto;
import com.spring.mvcproject.score.dto.response.ScoreDetailDto;
import com.spring.mvcproject.score.entity.Score;
import com.spring.mvcproject.score.response.ScoreListDto;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController // JSON응답
@RequestMapping("/api/v1/scores")
public class ScoreApiController {

    private Map<Long, Score> scoreStore = new HashMap<>();

    private Long nextId = 1L;

    public ScoreApiController() {
        Score s1 = new Score(nextId++, "김말복", 100, 88, 75);
        Score s2 = new Score(nextId++, "박수포자", 55, 95, 15);
        Score s3 = new Score(nextId++, "김마이클", 4, 100, 40);
        Score s4 = new Score(nextId++, "세종대왕", 100, 0, 90);

        scoreStore.put(s1.getId(), s1);
        scoreStore.put(s2.getId(), s2);
        scoreStore.put(s3.getId(), s3);
        scoreStore.put(s4.getId(), s4);
    }

    // 전체 성적정보 조회 (정렬 파라미터를 읽어야 함)
    // /api/v1/scores?sort=name
    @GetMapping
    public ResponseEntity<List<ScoreListDto>> scoreList(
            @RequestParam(required = false, defaultValue = "id") String sort
    ) {
//        // 1. DB에서 성적 정보를 모두 꺼내와서 values 만으로 리스트로 만듬
//         ArrayList<Score> originalScores = new ArrayList<>(scoreStore.values());
//        List<ScoreListDto> responseList = new ArrayList<>();
//        // 2.ArrayList<Score>에서 반복문으로 각 Score를 꺼내서
//        for (Score score : originalScores)
//             // Score 객체로 ScoreListDto 객체를 만들어서
//            ScoreListDto dto = new ScoreListDto(score);
//            // 리스트에 저장
//            responseList.add(dto);
//        }

        /* 구현하고자 하는 원리 : 원본 데이터인 HashMap<id, Score> ScoreList를 가져와서,
                              ScoreList에서 반복문으로 각 Score 객체에 접근한 후
                              Score 객체를 ScoreListDto 객체르 변환하여서 ScoreListDto List를 만들고
                              SCORElISTdTO lIST를 클라이언트에 전달해준다.
         */
        List<ScoreListDto> responseList = new ArrayList<>(scoreStore.values()) // hashmap에서 score 객체를 꺼낸 후에
                .stream()
                .map(score -> new ScoreListDto(score))// 각 객체를ScoreListDto로 변환해서 저장한 후에
                .collect(Collectors.toList()); // 리스트로 만든다.

        // 석차 구하기
        calculateRank(responseList);

        return ResponseEntity
                .ok()
                .body(responseList);
    }


    private void calculateRank(List<ScoreListDto> responseList) {
       // 석차 구하기
        // 총점 내림차로 정렬
        responseList.sort(Comparator.comparing(ScoreListDto::getAverage).reversed());

        int currentRank = 1;
        for (ScoreListDto dto : responseList) {
            dto.setRank(currentRank++);
        }
    }


    // 성적 상세조회 요청
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        // 데이터베이스(Map)에서 단일 조회 수행
        Score targetStudent = scoreStore.get(id);
        if (targetStudent == null) {
            return ResponseEntity
                    .status(404)
                    .body("해당 정보를 찾을 수 없습니다: id - " + id);
        }

        // 석차와 총 학생수를 구하기 위해 학생 목록을 가져옴
        List<Score> scoreList = new ArrayList<>(scoreStore.values());

        scoreList.sort(Comparator.comparing((Score s) -> s.getKor() + s.getEng() + s.getMath()).reversed());

        int rank = 1;
        for (Score s : scoreList) { // 전체 학생을 순회하면서
            if (s.getId().equals(targetStudent.getId())) { // 현재 발견된 학생을 찾으면 스톱
                break;
            }
            rank++;
        }

        // Score엔터티를 ScoreDetailDto로 변환
        ScoreDetailDto responseDto = new ScoreDetailDto(targetStudent, scoreList.size());
        responseDto.setRank(rank);

        return ResponseEntity
                .ok()
                .body(responseDto);
    }





    // 정렬 처리를 위한 정렬기 생성 유틸 메서드
    private Comparator<ScoreListDto> getScoreComparator(String sort) {
        Comparator<ScoreListDto> comparing = null;
        switch (sort) {
            case "id":
                comparing = Comparator.comparing(ScoreListDto::getId);
                break;
            case "name":
                comparing = Comparator.comparing(ScoreListDto::getMaskingName);
                break;
            case "average":
                comparing = Comparator.comparing(ScoreListDto::getAverage).reversed();
        }
        return comparing;
    }





    // 성적 정보 생성 요청 처리
    @PostMapping
    public ResponseEntity<?> createScore(
            // @Valid로 인해 객체 생성 시 유효성 검사가 실행되고
            @RequestBody @Valid ScoreCreateDto dto
            // 우효성 검사 결과는 BindingResult 객체에 저장된다.
            , BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) { // 입력값 검증에서 에러가 발생했다면
            // getFieldErrors()로 에러 상새내역 리스트를 받고,
            // 그 리스트에서 에러 필드랑, 에러 defaultMessage을 추출해서 프론트에 맵으로 전달할게요.
            /* fieldErrors의 형태 :
               [
                   { bindingFailure: false
                     defaultMessage: "어떠어떤게 잘못됐어요."
                     field: "number" // 객체의 필드
                   }
                 , { bindingFailure: false
                     defaultMessage: "어떠어떤게 잘못됐어요."
                     field: "number" // 객체의 필드
                    }
                ]
             */

            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                errorMap.put(err.getField(), err.getDefaultMessage());
            });
            return ResponseEntity
                    .badRequest()
                    .body(errorMap)
                    ;
        }


        // ScoreCreateDto를 Score로 변환하는 작업
        Score score = new Score(dto);
        score.setId(nextId++);

        scoreStore.put(score.getId(), score);
        return ResponseEntity
                .ok()
                .body("성적 정보 생성 완료! " + score);

    }

    // 성적 정보 삭제요청 처리
    @DeleteMapping("/{id}")
    public String deleteScore(
            @PathVariable Long id
    ) {
        scoreStore.remove(id);
        return "성적 정보 삭제 성공! - id: " + id;
    }




}