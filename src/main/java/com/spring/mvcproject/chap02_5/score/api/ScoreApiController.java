package com.spring.mvcproject.chap02_5.score.api;

import com.oracle.wls.shaded.org.apache.bcel.generic.NEW;
import com.spring.mvcproject.chap02_5.score.entity.Score;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/scores")
public class ScoreApiController {

    private Map<Long, Score> scoreStore = new HashMap<>();

    private Long nextId = 1L;

    public ScoreApiController() {
        Score s1 = new Score(nextId++, "김말복", 100, 88, 75);
        Score s2 = new Score(nextId++, "박수포", 100, 95, 15);
        Score s3 = new Score(nextId++, "김천재", 100, 100, 100);

        scoreStore.put(s1.getId(), s1);
        scoreStore.put(s2.getId(), s2);
        scoreStore.put(s3.getId(), s3);
    }

    // 전체 성적정보 조회
        @GetMapping
        public List<Score> scoreList() {
        return new ArrayList<>(scoreStore.values());

    }







}
