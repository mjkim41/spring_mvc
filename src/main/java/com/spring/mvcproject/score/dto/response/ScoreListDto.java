package com.spring.mvcproject.score.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.mvcproject.score.entity.Score;
import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ScoreListDto {
    private Long id; // 식별번호
    private String maskingName; // 마스킹 처리한 이름

    @JsonProperty("sum")
    private int total; //  국영수 성적 총점

    @JsonProperty("avg")
    private double average; // 국영수 성적 평균/3
    private int rank; // 석차

    public ScoreListDto(Score score) {
        this.id = score.getId();
        this.total = score.getEng() + score.getKor() + score.getMath();
        this.average = Math.round((total / 3.0) * 100) / 100.0;
        this.maskingName = makeMaskingName(score.getName());
    }

    /**
     * 원본 이름을 첫글자 마지막 글자만 보여주고 나머지 별 처리
     * 만약 이름이 2글자라면, 첫글자만 보여주고 두번째 글자는 * 처리
     * @param originalName - 마스킹이 안 된 원본 이름
     * @return - 마스킹 처리된 이름
     */
    private String makeMaskingName(String originalName) {
        // 이름이 2글자인 사람 처리
        if (originalName.length() <= 2) {
            return originalName.charAt(0) + "*";
        }
        // 나머지 경우
        char firstLetter = originalName.charAt(0); // 첫글자 추출
        char lastLetter = originalName.charAt(originalName.length() - 1); // 끝 글자 추출

        String maskingName = String.valueOf(firstLetter); // char를 string으로 변환
        for (int i = 1; i < originalName.length() - 1; i++) {
            maskingName += "*";
        }
        maskingName += lastLetter;
        return maskingName;

    }

    @Getter @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScoreDetailDto {

        private Long id;
        @JsonProperty("fullName")
        private String name;
        private int kor, eng, math;
        private int total;
        private double average;

        @Setter
        private int rank;
        private int totalCount; // 총 학생 수

        public ScoreDetailDto(Score s, int totalCount) {
            this.id = s.getId();
            this.name = s.getName();
            this.kor = s.getKor();
            this.eng = s.getEng();
            this.math = s.getMath();
            this.total = kor + eng + math;
            this.average = Math.round(total / 3.0 * 100) / 100.0;
            this.totalCount = totalCount;

        }
    }
}
