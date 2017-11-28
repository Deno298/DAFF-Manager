package dk.KeaExam.controller;

import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

}
