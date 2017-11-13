package dk.KeaExam.controller;

import dk.KeaExam.model.User;
import dk.KeaExam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/editaccount")
    public String deleteUserForm(){
        return "editaccount";
    }


    @PostMapping("/editaccount")
    public String userDelete(@ModelAttribute User user, ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User userExists = (userRepository.findByUsername(name));
        if (userExists != null && userExists.getLigaer() == null) {
            userRepository.delete(userExists);
            return "landingpage";
        }
        model.addAttribute("errorMsg", "You are not allowed to delete your account");
        return "editaccount";
    }
}
