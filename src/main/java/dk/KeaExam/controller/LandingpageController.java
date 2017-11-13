package dk.KeaExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LandingpageController {

    @RequestMapping("/landingpage")
    public String landingpage(){
        return "landingpage";
    }

}
