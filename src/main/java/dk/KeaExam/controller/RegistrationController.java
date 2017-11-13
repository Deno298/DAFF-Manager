package dk.KeaExam.controller;

import dk.KeaExam.service.CustomUserDetailService;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;

        @GetMapping("/signup")
        public String signUp(Model model) {
            model.addAttribute("user", new User());
            return "signup";
        }

        @PostMapping("/signup")
        public String signUp(@ModelAttribute User user, BindingResult bindingResult) {
            User userExists = (userRepository.findByUsername(user.getUsername()));
            if(userExists != null){
                bindingResult.rejectValue("username", "error.user", "There is already a user with that username");
            }
            if(bindingResult.hasErrors()){
                return"signup";
            }
            customUserDetailService.registerUser(user);
            return "landingpage";
        }
    }


