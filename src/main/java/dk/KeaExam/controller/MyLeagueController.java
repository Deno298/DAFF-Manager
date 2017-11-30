package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.Player;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.PlayerRepository;
import dk.KeaExam.repository.TeamRepository;
import dk.KeaExam.repository.UserRepository;
import dk.KeaExam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class MyLeagueController {

    @Autowired
    private UserService userService;

    @RequestMapping("/myLeagues")
    public ModelAndView showMyLeagues() {
        User user = userService.getCurrentUser();
        return new ModelAndView("myLeagues", "myLeagues", user.getLeagues());
    }

}