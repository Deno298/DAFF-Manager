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
        return new ModelAndView("leagueoverview", "leagueoverview", leagueRepository.findAll());
    }

    @PostMapping("/leagueoverview")
    public ModelAndView signUpForLeague(@ModelAttribute User user, BindingResult bindingResult, @RequestParam("paramName") Integer league_id, @RequestParam("password") String password, @RequestParam("teamName") String teamName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        League league = leagueRepository.getOne(league_id);
        Team teamExist = teamRepository.findByTeamName(teamName);

        if(league.getPassword().equals(password) && teamExist == null){
            User userExists = (userRepository.findByUsername(name));
            userExists.addLeague(league);
            Team team = new Team();
            team.setTeamName(teamName);
            league.addTeams(team);
            userExists.addTeams(team);
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