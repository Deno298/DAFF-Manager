package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import dk.KeaExam.repository.UserRepository;
import dk.KeaExam.service.LeagueService;
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



@Controller
public class CreateLeagueController {

    @Autowired
    private UserService userService;

    @Autowired
    private LeagueService leagueService;


    @GetMapping("/createleague")
    public String CreateLeague(Model model) {
        model.addAttribute("league", new League());
        return "createleague";
    }

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
