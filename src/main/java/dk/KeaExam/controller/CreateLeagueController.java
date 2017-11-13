package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CreateLeagueController {

    @Autowired
    private LeagueRepository leagueRepository;

    @GetMapping("/createleague")
    public String CreateLeague(Model model) {
        model.addAttribute("league", new League());
        return "createleague";
    }

    @PostMapping("/createleague")
    public String CreateLeague(League league){
        leagueRepository.save(league);
        return "landingpage";
    }

}
