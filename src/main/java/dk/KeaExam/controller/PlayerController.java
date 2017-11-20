package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.Player;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.PlayerRepository;
import dk.KeaExam.repository.TeamRepository;
import dk.KeaExam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/myLeagues")
    public ModelAndView showMyLeagues() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByUsername(name);
        return new ModelAndView("myLeagues", "myLeagues", user.getLeagues());
    }

    @PostMapping("/search")
    public String addPlayer(){
        User user = userRepository.getOne(1);
        Team team = new Team();
        team.setTeamName("rød");
        user.addTeams(team);
        teamRepository.save(team);
        userRepository.save(user);
        return "search";
    }




    /*
    @PostMapping("/search")
    public String addPlayer(@ModelAttribute Team team) {
        Long a = new Long(1);
        team = teamRepository.getOne("sol");
        Player player = playerRepo.getOne(a);
        team.addPlayer(player);
        teamRepository.save(team);
        return "search";
    } */
}