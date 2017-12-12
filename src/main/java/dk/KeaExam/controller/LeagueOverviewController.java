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

/**
 * This class is responsible for handling view requests to leagueoverview and the communication with
 * the necessary services to sign add a user to a league.
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Controller
public class LeagueOverviewController {

    @Autowired
    private UserService userService;

    @Autowired
    private LeagueService leagueService;

    /**
     * Redirecting the user to the leagueoverview.
     * @return ModelAndview containing all available leagues.
     */
    @GetMapping("/leagueoverview")
    public ModelAndView showAllAvailableLeagues() {
        return new ModelAndView("leagueoverview", "leagueoverview", leagueService.findAllAvailableLeagues() );
    }

    /**
     * Handle post requests from leagueoverview, add user to a league
     * @param leagueId The selected leagues id
     * @param password The leagues password
     * @param teamName The users requested teamname
     * @param model model containing all available leagues
     * @return if the user has succesfully been added return index view, else return leagueoverview
     */
    @PostMapping("/leagueoverview")
    public String signUpForLeague(@RequestParam("leagueId") Integer leagueId, @RequestParam("password") String password, @RequestParam("teamName") String teamName, Model model) {

        //adds user to league
        leagueService.joinLeague(leagueId, password, teamName, model);
        //if the model contains errormessages
        if(model.containsAttribute("error")){
            //return the leagueoverview page again

            model.addAttribute("leagueoverview", leagueService.findAllAvailableLeagues());
            return "leagueoverview";
        }

        //return the index page
        model.addAttribute("league", new League());
        model.addAttribute("userLeagues", userService.getCurrentUser().getLeagues());

        return "index";

        }

}