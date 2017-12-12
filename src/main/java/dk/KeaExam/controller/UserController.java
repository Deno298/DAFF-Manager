package dk.KeaExam.controller;

import dk.KeaExam.model.User;
import dk.KeaExam.repository.UserRepository;
import dk.KeaExam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Class responsible for user editing functionality
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Redirects the user to the editaccount view
     * @return Editaccount view
     */
    @GetMapping("/editaccount")
    public String deleteUserForm(){
        return "editaccount";
    }


    /**
     * Handling post request to /editaccount. Deleting user functionality
     * @param user Currently logged in user
     * @param model Model containing error msg
     * @return edit account view
     */
    @PostMapping("/editaccount")
    public String userDelete(@ModelAttribute User user, ModelMap model) {
        user = userService.getCurrentUser();
        if (user != null && user.getLeagues() == null) {
            userRepository.delete(user);
            return "landingpage";
        }
        model.addAttribute("errorMsg", "You are not allowed to delete your account");
        return "editaccount";
    }

    @GetMapping("/myAccount")
    public String myAccountGet(){
        return "myAccount";
    }
}
