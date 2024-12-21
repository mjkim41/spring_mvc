package com.spring.mvcproject.personalProject.travel.api;

import com.spring.mvcproject.personalProject.travel.dto.response.flight.FlightWithStatusCheckCode;
import com.spring.mvcproject.personalProject.travel.entity.Flight;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TravelApiController {

    // flight 정보를 담을 해쉬맵(이름으로 매핑)
    private Map<String, Flight> flightMap = new HashMap<>();

    public TravelApiController() {
        // flight 객체 생성
        Flight passenger1 = new Flight("MJ",
                "TR841",
                LocalDateTime.of(2025, 1, 25, 20, 45),
                LocalDateTime.of(2025, 1, 26, 2, 25),
                "ICN", "SGP", 0,
                "TR024",
                LocalDateTime.of(2025, 1, 26, 11, 0),
                LocalDateTime.of(2025, 1, 26, 21, 20),
                "SGP", "MEL", 0
        );
        Flight passenger2 = new Flight("KW",
                "CZ654",
                LocalDateTime.of(2025, 1, 11, 20, 25),
                LocalDateTime.of(2025, 1, 12, 15, 00),
                "LGW", "CGO", 10.35,
                "CZ6009",
                LocalDateTime.of(2025, 1, 12, 18, 30),
                LocalDateTime.of(2025, 1, 12, 21, 45),
                "CGO", "ICN", 2.15
        );
        //flight 객체 해쉬맵에 담기
        flightMap.put(passenger1.getPassengerName(), passenger1);
        flightMap.put(passenger2.getPassengerName(), passenger2);
    }


    // 화면 초기 진입 시 Flight 객체 리스트 조회하여 (여행자 선택 버튼용) 여행자 이름만 객체로 생성하여 조회
    @GetMapping("/api/v1/flight")
    public ResponseEntity<?> flightByPassenger() {

        // list로 변환 + 이름만 추출
        List<String> flightList = new ArrayList<>(flightMap.values())
                .stream()
                .map(Flight::getPassengerName)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(flightList);
    }

    // ======= 선택된 자 비행정보 조회 ====
    @GetMapping("/api/v1/flight/{passengerName}")
    public ResponseEntity<?> flightByPassenger(
            // 클라이언트가 passengerName전달
            @PathVariable String passengerName
    ) {
        // map에서 해당 승객 있는지 조회
        Flight foundPassenger = flightMap.get(passengerName);

        // 승객 없으면 에러 메시지 반환
        if(foundPassenger == null) {
            return ResponseEntity.badRequest().body("존재하지 않는 승객입니다");
        }

        // 승객있으면 승객 정보 전달
        FlightWithStatusCheckCode data = new FlightWithStatusCheckCode(foundPassenger);
        return ResponseEntity
                .ok()
                .body(data);

    }
}
