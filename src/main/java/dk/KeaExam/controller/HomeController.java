package dk.KeaExam.controller;

import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling view requests to the home view.
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Controller
public class HomeController {

    /**
     * Redirects the user to the home screen
     * @param model Model to get passed on to the view with a user object
     * @return The home view
     */
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

}
