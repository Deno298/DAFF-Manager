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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



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
    public String CreateLeague(League league,@RequestParam("year") String year, @RequestParam("month") String month,
                               @RequestParam("dayOfMonth") String dayOfMonth, @RequestParam("hour") String hour, @RequestParam("minute") String minute,
                               @RequestParam("draftFormat") int draftFormat, @RequestParam("leagueFormat") int leagueFormat){

        //Finding currently logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByUsername(name);

        //Setting the draft date
        draftdate(year, month, dayOfMonth, hour, minute, league);

        //setting owner of the league
        league.setOwnerid(user.getId());

        //saving league
        leagueRepository.save(league);
        return "landingpage";
    }

    public void draftdate (String year, String month, String day, String hour, String minute, League league) {

        String dateString = year + "-" +  month + "-" + day + " " + hour + ":" + minute;
        System.out.println(dateString);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime draftDate = LocalDateTime.parse(dateString, dtf);
        league.setDraftDate(draftDate);
        System.out.println(draftDate);
    }

}
