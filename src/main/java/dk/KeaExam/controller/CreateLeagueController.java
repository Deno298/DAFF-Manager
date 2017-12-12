package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import dk.KeaExam.repository.UserRepository;
import dk.KeaExam.service.LeagueService;
import dk.KeaExam.service.TeamService;
import dk.KeaExam.service.UserService;
import dk.KeaExam.service.UserServiceImpl;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is responsible for handling view requests to create league and the communication with
 * the necessary services to create a league.
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */

@Controller
public class CreateLeagueController {

    @Autowired
    private UserService userService;

    @Autowired
    private LeagueService leagueService;

    /**
     * Handle post requests from /createleague.
     * @param league League containing the users chosen leagueName and leaguePassword.
     * @param year Year of the desired draft date.
     * @param month Month of the desired draft date.
     * @param dayOfMonth DayOfMonth of the desired draft date.
     * @param hour Hour of the desired draft date.
     * @param minute Minute of the desired draft date.
     * @param teamName Users desired teamname.
     * @param model Model to get passed on to view.
     * @return Index view
     */
    @PostMapping("/createleague")
    public String CreateLeague(League league, @RequestParam("year") String year, @RequestParam("month") String month, @RequestParam("dayOfMonth") String dayOfMonth, @RequestParam("hour") String hour,
                               @RequestParam("minute") String minute, @RequestParam("wishedTeamName") String teamName, Model model){

        User user = userService.getCurrentUser();
        model = leagueService.createLeague(league, year, month, dayOfMonth, hour, minute, teamName, model);

        model.addAttribute("userLeagues", user.getLeagues());
        model.addAttribute("league", new League());

        return "index";
    }
}
