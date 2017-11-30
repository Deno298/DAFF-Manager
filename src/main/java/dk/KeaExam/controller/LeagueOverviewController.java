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
    public ModelAndView signUpForLeague(@ModelAttribute User user, BindingResult bindingResult,
                                        @RequestParam("leagueId") Integer leagueId, @RequestParam("password") String password, @RequestParam("teamName") String teamName, Model model) {

        //finding the league the user pressed on
        League league = leagueService.getOneLeague(leagueId);

        //checks if the users requested teamname already exists in the database
        Team teamExist = teamService.findByTeamName(teamName);

        model.addAttribute("league", new League());
        if(league.getPassword().equals(password) && teamExist == null){
            userService.addUserToLeague(league, teamName, user);
            model.addAttribute("league", new League());
            model.addAttribute("userLeagues", userService.getCurrentUser().getLeagues());
            return new ModelAndView("index", "league", new League());
        } else {
            bindingResult.rejectValue("password", "Error.password", "der er fejl i dit kodeord");
            return new ModelAndView("leagueoverview", "leagueoverview", leagueService.findAllAvailableLeagues() );
        }
    }
}