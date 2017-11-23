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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
public class LeagueDetailsController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/leaguedetails")
    public ModelAndView LeagueDetails(Model model, @RequestParam("league_id") int league_id) {

        //Finder ligaen brugeren ønsker at se details for
        League league = leagueRepository.getOne(league_id);
        model.addAttribute("drafttime", league);

        //Finder brugeren
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByUsername(name);

        //Finder brugerens hold i ligaen
        for (Team team : user.getTeams()) {
            if (team.getLeague_id() == league_id) {
                model.addAttribute("userTeam", team);
            }
        }

        //Get all the teams from the selected league and sorting the list based on points.. see team comparable.
        List<Team> teams = league.getTeams();
        Collections.sort(teams);

        //Stillingen
        model.addAttribute("leagueTeams", teams);

        //Generating draft-order
        List<User> draftOrder = createDraftOrder(new ArrayList<>(league.getUsers()), "sne");

        model.addAttribute("draftOrder", draftOrder);

        //return det hele til draft siden
        return new ModelAndView("leagueDetails", "leagueDetails", model);
    }



    public List<User> createDraftOrder(ArrayList<User> usersInLeague, String draftType) {

        int repeater = 3;
        //list we wants to return
        List<User> draftOrder = new ArrayList<>();

        //Randomizes order of users, users comparable method returns a random value
        Collections.sort(usersInLeague);

        //adds user to final list
        draftOrder.addAll(usersInLeague);

        if(draftType.equals("snake")) {
            Collections.reverse(usersInLeague);
            draftOrder.addAll(usersInLeague);
            repeater = 2;
        }
        for (int i = 0; i < repeater; i++) {
            draftOrder.addAll(draftOrder);
        }

        return draftOrder;
    }

}