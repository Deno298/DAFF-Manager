package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.User;
import dk.KeaExam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class is responsible for handling view requests to the index view.
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    /**
     * Redirects the user to the home screen.
     * @param model Model to get passed on to the view containing a league object and a list containing all the users leagues.
     * @return The index view.
     */
    @RequestMapping("/index")
    public String index(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("league", new League());
        model.addAttribute("userLeagues", user.getLeagues());
        return "index";
    }

}
