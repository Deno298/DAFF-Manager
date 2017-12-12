package dk.KeaExam.controller;

import dk.KeaExam.service.CustomUserDetailService;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.UserRepository;
import dk.KeaExam.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is responsible for handling post requests to the signup url.
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Controller
public class RegistrationController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private UserService userService;

    /**
     * Handle post request to /signup
     * @param user The users information is contained in this object
     * @param bindingResult Bindingresult catching errors
     * @return The view Home
     */
    @PostMapping("/signup")
    public String signUp(@ModelAttribute User user, BindingResult bindingResult) {


        User userExists = userService.findByUsername(user.getUsername());

        if(userExists != null){

            bindingResult.rejectValue("username", "error.user", "There is already a user with that username");
        }

        if(bindingResult.hasErrors()){
            return"home";
        }
        customUserDetailService.registerUser(user);
        return "home";
    }


}


