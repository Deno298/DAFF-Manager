package dk.KeaExam.controller;

import dk.KeaExam.model.MyUserDetailsService;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private MyUserDetailsService myUserDetailsService;

        @GetMapping("/registration")
        public String greetingForm(Model model) {
            model.addAttribute("user", new User());
            return "registration";
        }

        @PostMapping("/registration")
        public String greetingSubmit(@ModelAttribute User greeting, BindingResult bindingResult) {
            User userExists = (userRepository.findByUsername(greeting.getUsername()));
            if(userExists != null){
                bindingResult.rejectValue("username", "error.user", "There is already a user with that username");
            }
            if(bindingResult.hasErrors()){
                return"registration";
            }
            myUserDetailsService.registerUser(greeting);
            return "/result";
        }
    }


