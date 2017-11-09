package dk.KeaExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyAccountController {

    @GetMapping("/myAccount")
    public String myAccountGet(){
        return "myAccount";
    }
}
