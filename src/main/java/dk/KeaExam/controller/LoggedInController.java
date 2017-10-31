package dk.KeaExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoggedInController {

    @RequestMapping("/hello")
    public String greetings(){
        return "landingpage";
    }

    @RequestMapping("/layout")
    public String layout() {
        return "layout";
    }
}
