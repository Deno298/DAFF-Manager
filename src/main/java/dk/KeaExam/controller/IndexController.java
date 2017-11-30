package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.User;
import dk.KeaExam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("league", new League());
        model.addAttribute("userLeagues", user.getLeagues());
        return "index";
    }

}
