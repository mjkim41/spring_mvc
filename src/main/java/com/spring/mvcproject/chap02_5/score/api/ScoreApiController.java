package com.spring.mvcproject.chap02_5.score.api;

import com.spring.mvcproject.chap02_5.score.entity.Score;
import com.spring.mvcproject.chap02_5.score.entity.Score;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/scores")
public class ScoreApiController {

    /* List가 아닌 Map으로 받는 이유 :
     List는 예를 들어 id로 정보를 찾으려면 for 문을 돌리거나 index 번호를 알아야 되는데,
     Map은 id를 key로 해 놓으면 Map.get(key);으로 쉽게 값 가져 올 수 있음
     */
    private Map<Long, Score> scoreStore = new HashMap<>();

    private Long nextId = 1L;

    // 생성자에 넣어 놓으면 @controller에 의해서 객체 자동으로 생성됨
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

    // ========= 전체 성적정보 조회 ======== //
    // /api/v1/scores?sort=name 사용자가 이렇게 보내면,
    @GetMapping
    public List<Score> scoreList(
            // 사용자가 ?sorted=name 이런 식으로 보냄
            @RequestParam(required = false, defaultValue = "id") String sort
    ) {

        System.out.println("정렬기준: " + sort);

        // 사용자가 보낸 기준에 따라(getScoreComparator()함수에서 가져옴) 정렬한 scoreList을 보냄
        return new ArrayList<>(scoreStore.values())
                .stream()
                .sorted(getScoreComparator(sort))
                .collect(Collectors.toList())
                ;
    }

    // 성적 정보 생성 요청 처리
    @PostMapping
    public String createScore(
            /* @RequestBody Object 변수명 :
             클라이언트가 json 형태로 보내온 데이터를 받아서
             -> 변수명 = new Object()로 새 객체를 생성한다
             -> 이 때, 가져온 Object의 key 값과 일치하는 객체 필드값이 있으면,
                그 객체 필드의 값도 key value 값으로 자동 세팅된다.
            */
           @RequestBody Score score
    ) {
        score.setId(nextId++);
        scoreStore.put(score.getId(), score);
        return "성적 정보 생성 완료!";
    }

    private static Comparator<Score> getScoreComparator(String sort) {
        Comparator<Score> comparing = null;
        switch (sort) {
            case "id":
                comparing = Comparator.comparing(Score::getId);
                break;
            case "name":
                comparing = Comparator.comparing(Score::getName);
                break;
        }
        return comparing;
    }

    // 성적 정보 삭제요청 처리
    @DeleteMapping("/{id}")
    public String deleteScore(
            @PathVariable Long id
    ) {
        scoreStore.remove(id);
        return "성적 정보 삭제 성공 - id" + id;
    }

}

