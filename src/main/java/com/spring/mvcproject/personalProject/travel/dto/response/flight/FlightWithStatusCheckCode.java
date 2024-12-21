package com.spring.mvcproject.personalProject.travel.dto.response.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.mvcproject.personalProject.travel.entity.Flight;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString @EqualsAndHashCode
public class FlightWithStatusCheckCode {

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

    // 이 객채에서 추가한 필드
    String airlineCode1;
    String flightNumNumbers1;
    String airlineCode2;
    String flightNumNumbers2;
    int flightYear1;
    int flightMonth1;
    int flightDay1;
    int flightYear2;
    int flightMonth2;
    int flightDay2;

    public FlightWithStatusCheckCode(Flight flight) {
        this.passengerName = flight.getPassengerName();

        this.flightNumber1 = flight.getFlightNumber1();
        this.departureTime1 = flight.getDepartureTime1();
        this.arrivalTime1 = flight.getArrivalTime1();
        this.departureLocation1 = flight.getDepartureLocation1();
        this.arrivalLocation1 = flight.getArrivalLocation1();
        this.duration1 = flight.getDuration1();

        this.flightNumber2 = flight.getFlightNumber2();
        this.departureTime2 = flight.getDepartureTime2();
        this.arrivalTime2 = flight.getArrivalTime2();
        this.departureLocation2 = flight.getDepartureLocation2();
        this.arrivalLocation2 = flight.getArrivalLocation2();
        this.duration2 = flight.getDuration2();

        // 이 객체에서 추가 된 값들
        airlineCode1 = flightNumber1.replaceAll("[^a-zA-Z]", ""); // 문자는 남기고 나머지 제거
        flightNumNumbers1= flightNumber1.replaceAll("[^0-9]", ""); // 숫자는 남기고 나머지 제거
        airlineCode2 = flightNumber2.replaceAll("[^a-zA-Z]", ""); // 문자는 남기고 나머지 제거
        flightNumNumbers2 = flightNumber2.replaceAll("[^0-9]", ""); // 숫자는 남기고 나머지 제거

        flightYear1 = departureTime1.getYear();
        flightMonth1 = departureTime1.getMonthValue();
        flightDay1 = departureTime1.getDayOfMonth();
        flightYear2 = departureTime1.getYear();
        flightMonth2 = departureTime1.getMonthValue();
        flightDay2 = departureTime1.getDayOfMonth();


    }
}
