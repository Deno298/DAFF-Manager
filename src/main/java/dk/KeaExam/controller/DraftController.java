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


/**
 * This class is responsible for handling view requests to the draft view.
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Controller
public class DraftController {

    @Autowired
    private LeagueService leagueService;

    /**
     * Redirects the user to the draft view.
     * @param model Model to get passed on to the view .
     * @param leagueid The leagueid for the selected league.
     * @return The model and view.
     */
    @GetMapping("/draft")
    public ModelAndView draftView(Model model, @RequestParam("league") int leagueid){
        League league  = leagueService.getOneLeague(leagueid);
        model.addAttribute("league" , league);
        List<User> draftOrder = leagueService.generateDraftOrder(leagueid);
        model.addAttribute("draftOrder", draftOrder);
        model.addAttribute("currentDrafter", draftOrder.get(0) );

        return new ModelAndView("draft", "draft",model);
    }

}