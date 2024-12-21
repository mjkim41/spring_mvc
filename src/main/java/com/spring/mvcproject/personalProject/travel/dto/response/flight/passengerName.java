package com.spring.mvcproject.personalProject.travel.dto.response.flight;

import com.spring.mvcproject.personalProject.travel.entity.Flight;
import lombok.*;
import org.springframework.stereotype.Controller;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor
public class PassengerName {

    private String passengerName;

    public PassengerName(Flight flight) {
        this.passengerName = flight.getPassengerName();
    }

}
