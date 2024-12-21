package com.spring.mvcproject.personalProject.travel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class Flight {

        // 공통
        private String passengerName;

        // 첫 번째 항공편
        private String flightNumber1;         // 첫 번째 항공편 번호 (예: CZ654)
        @JsonFormat(pattern = "d.MMM(E) h:mma", locale = "en_AU")
        private LocalDateTime departureTime1; // 첫 번째 항공편 출발 시간
        @JsonFormat(pattern = "d.MMM(E) h:mma", locale = "en_AU")
        private LocalDateTime arrivalTime1;   // 첫 번째 항공편 도착 시간
        private String departureLocation1;    // 첫 번째 항공편 출발지 (예: 서울)
        private String arrivalLocation1;      // 첫 번째 항공편 도착지 (예: 중국)
        private double duration1;             // 첫 번째 항공편 비행 시간 (예: 11시간 35분)

        // 두 번째 항공편
        private String flightNumber2;         // 두 번째 항공편 번호 (예: CZ6009)
        @JsonFormat(pattern = "d.MMM(E) h:mma", locale = "en_AU")
        private LocalDateTime departureTime2; // 두 번째 항공편 출발 시간
        @JsonFormat(pattern = "d.MMM(E) h:mma", locale = "en_AU")
        private LocalDateTime arrivalTime2;   // 두 번째 항공편 도착 시간
        private String departureLocation2;    // 두 번째 항공편 출발지 (예: 중국)
        private String arrivalLocation2;      // 두 번째 항공편 도착지 (예: 서울)
        private double duration2;             // 두 번째 항공편 비행 시간 (예: 4시간 15분)




}
