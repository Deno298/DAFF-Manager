package dk.KeaExam.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import dk.KeaExam.model.League;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import dk.KeaExam.repository.PlayerRepository;
import dk.KeaExam.repository.TeamRepository;
import dk.KeaExam.repository.UserRepository;
import dk.KeaExam.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DraftController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    private LeagueService leagueService;

    @GetMapping("/draft")
    public ModelAndView draftPhase(Model model, @RequestParam("league") int leagueid){
        League league  = leagueService.getOneLeague(leagueid);
        model.addAttribute("league" , league);

        List<User> draftOrder = leagueService.generateDraftOrder(leagueid);
        model.addAttribute("draftOrder", draftOrder);
        model.addAttribute("currentDrafter", draftOrder.get(0) );




        return new ModelAndView("yay", "draft",model);
    }


    @PostMapping("/draft")
    public String addPlayer(@RequestParam("teamName") String teamName, @RequestParam("playerId") int playerId,
                                @RequestParam("leagueId") int leagueId ){





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