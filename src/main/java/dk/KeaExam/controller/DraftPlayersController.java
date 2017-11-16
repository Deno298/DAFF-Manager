package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import dk.KeaExam.repository.PlayerRepository;
import dk.KeaExam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DraftPlayersController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/draft")
    public ModelAndView CreateLeague(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByUsername(name);
        return new ModelAndView("leagueoverview", "leagueoverview", user);
    }
}
