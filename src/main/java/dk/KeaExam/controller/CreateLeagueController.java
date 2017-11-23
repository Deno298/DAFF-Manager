package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import dk.KeaExam.repository.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;


@Controller
public class CreateLeagueController {

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/createleague")
    public String CreateLeague(Model model) {
        model.addAttribute("league", new League());
        return "createleague";
    }

    @PostMapping("/createleague")
    public String CreateLeague(League league,@RequestParam("year") int year, @RequestParam("month") int month,
                               @RequestParam("dayOfMonth") int dayOfMonth, @RequestParam("hour") int hour, @RequestParam("minute") int minute,
                               @RequestParam("draftformat") int draftFormat, @RequestParam("leagueformat") int leagueFormat){

        //Finding currently logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByUsername(name);

        //Setting the draft date
        LocalDateTime date = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        league.setDraftDate(date);


        //setting owner of the league
        league.setOwnerid(user.getId());

        //saving league
        leagueRepository.save(league);
        return "landingpage";
    }

}
