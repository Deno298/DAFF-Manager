package dk.KeaExam.controller;

import dk.KeaExam.model.League;
import dk.KeaExam.model.MatchSchedule;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import dk.KeaExam.repository.MatchScheduleRepository;
import dk.KeaExam.repository.TeamRepository;
import dk.KeaExam.repository.UserRepository;
import dk.KeaExam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
public class LeagueDetailsController {

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private DraftService draftService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchScheduleService matchScheduleService;

    @GetMapping("/leaguedetails")
    public ModelAndView LeagueDetails(Model model, @RequestParam("leagueId") int leagueId) {

        //Finder ligaen brugeren ønsker at se details for
        League league = leagueService.getOneLeague(leagueId);

        model.addAttribute("league", league);
        model.addAttribute("userTeam", teamService.findLoggedInUserTeam(leagueId));
        model.addAttribute("draftstring", draftService.convertDatetoString(league));
        // Skal ske på et senere tidspunkt matchScheduleService.generateMatchSchedule(league);

        //Stillingen
        model.addAttribute("leagueTeams", leagueService.getStandings(league));
       // Skal ske på et senere tidspunkt model.addAttribute("draftOrder", leagueService.generateDraftOrder(new ArrayList<>(league.getUsers()), "sne"));

        //return det hele til draft siden
        return new ModelAndView("leagueDetails", "leagueDetails", model);
    }


}