package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Team;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DraftPlayersController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/leagueDetails")
    public ModelAndView CreateLeague(Model model, @RequestParam("league_id") int league_id) {

        //Finder ligaen brugeren ønsker at se details for
        League league = leagueRepository.getOne(league_id);
        League leaguee = leagueRepository.getOne(2);


        //Finder brugeren
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByUsername(name);

        //Finder brugerens hold i ligaen
        for (Team team : user.getTeams()){
            if(team.getLeague_id() == league_id){
                model.addAttribute("userTeam", team);
                System.out.println(team);
            }
        }

        //et overview over de andre hold der er med i ligaen
        model.addAttribute("leagueTeams", league.getTeams());

        return new ModelAndView("leagueDetails", "leagueDetails", model);

        //Tid til draft

        //Stillingen

        //return det hele til draft siden

    }
}
