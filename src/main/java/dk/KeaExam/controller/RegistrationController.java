package dk.KeaExam.controller;

import dk.KeaExam.model.MyUserDetailsService;
import dk.KeaExam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegistrationController {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

        @GetMapping("/registration")
        public String greetingForm(Model model) {
            model.addAttribute("user", new User());
            return "registration";
        }

        @PostMapping("/registration")
        public String greetingSubmit(@ModelAttribute User greeting) {
            myUserDetailsService.registerUser(greeting);
            return "/result";
        }
    }


