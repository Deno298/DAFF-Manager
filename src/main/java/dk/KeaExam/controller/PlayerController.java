package dk.KeaExam.controller;

import dk.KeaExam.Entitys.Team;
import dk.KeaExam.model.Liga;
import dk.KeaExam.model.Player;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.PlayerRepository;
import dk.KeaExam.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import static java.lang.Math.toIntExact;

@Controller
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping("/search")
    public ModelAndView getPlayers() {
        return new ModelAndView("search", "search", playerRepo.findAll());
    }

    @PostMapping("/search")
    public String addPlayer(@ModelAttribute Team team) {
        Long a = new Long(1);
        team = teamRepository.getOne("sol");
        Player player = playerRepo.getOne(a);
        team.addPlayer(player);
        teamRepository.save(team);
        return "search";
    }
}