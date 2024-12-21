package com.spring.mvcproject.personalProject.travel.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/travel")
public class TravelPageController {

    // 메인 페이지
    @GetMapping("")
    public String getMainPage() {
        return "/personalProject/travel/main";
    }

    @GetMapping("/flight")
    public String getFlightInfo() {
        return "/personalProject/travel/flight";
    }

    @GetMapping("/activities")
    public String getActivitiesInfo() {
         return "forward:/personalProject/travel/activities.html";
//        return "/personalProject/travel/activities";
    }


}
