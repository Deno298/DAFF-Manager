package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import dk.KeaExam.repository.TeamRepository;
import dk.KeaExam.repository.UserRepository;
import dk.KeaExam.service.LeagueService;
import dk.KeaExam.service.TeamService;
import dk.KeaExam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;

@Controller
public class LeagueOverviewController {

    @Autowired
    private UserService userService;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/leagueoverview")
    public ModelAndView showAllAvailableLeagues() {
        return new ModelAndView("leagueoverview", "leagueoverview", leagueService.findAllAvailableLeagues() );
    }


    @PostMapping("/leagueoverview")
    public String signUpForLeague(@RequestParam("leagueId") Integer leagueId, @RequestParam("password") String password, @RequestParam("teamName") String teamName, Model model) {


        leagueService.joinLeague(leagueId, password, teamName, model);
        if(model.containsAttribute("error")){
            model.addAttribute("leagueoverview", leagueService.findAllAvailableLeagues());
            return "leagueoverview";
        }

        System.out.println(model.toString());
        model.addAttribute("league", new League());
        model.addAttribute("userLeagues", userService.getCurrentUser().getLeagues());

        return "index";

        }

}