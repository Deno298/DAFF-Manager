package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import dk.KeaExam.repository.TeamRepository;
import dk.KeaExam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;

@Controller
public class LeagueController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping("/leagueoverview")
    public ModelAndView showAllLeagues() {
        //Finds the logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByUsername(name);

        //Get all leagues
        List<League> leagues = leagueRepository.findAll();

        //Iterate through them, if the user exist remove the league from the list
        for (Iterator<League> it = leagues.iterator(); it.hasNext();){
            League league = it.next();
            if(league.containsUser(user)){
                it.remove();
            }
        }

        System.out.println(leagues);

        return new ModelAndView("leagueoverview", "leagueoverview", leagues);
    }

    @PostMapping("/leagueoverview")
    public ModelAndView signUpForLeague(@ModelAttribute User user, BindingResult bindingResult, @RequestParam("paramName") Integer league_id, @RequestParam("password") String password, @RequestParam("teamName") String teamName) {

        //Finding currently loggedin user name
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        //finding the league the user pressed on
        League league = leagueRepository.getOne(league_id);

        //checks if the users requested teamname already exists in the database
        Team teamExist = teamRepository.findByTeamName(teamName);
        if(league.getPassword().equals(password) && teamExist == null){
            //retrieve the user from the database so we can add him to the league
            User userExists = (userRepository.findByUsername(name));
            userExists.addLeague(league);

            //creates and adds team
            Team team = new Team();
            team.setTeamName(teamName);
            league.addTeams(team);
            userExists.addTeams(team);

            //saving
            teamRepository.save(team);
            userRepository.save(userExists);
            return new ModelAndView("landingpage", "landingpage", user);
        }
        else {
            bindingResult.rejectValue("password", "Error.password", "der er fejl i dit kodeord");
            return new ModelAndView("leagueoverview", "leagueoverview", leagueRepository.findAll());
        }
    }
}