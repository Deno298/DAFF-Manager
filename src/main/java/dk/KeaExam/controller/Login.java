package dk.KeaExam.controller;

import dk.KeaExam.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {

    @RequestMapping("/login")
    public String index(Model model) {
        return "login";
    }

}
