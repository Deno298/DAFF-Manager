package dk.KeaExam.controller;


import dk.KeaExam.model.*;
import dk.KeaExam.repository.PlayerRepository;
import dk.KeaExam.service.LeagueService;
import dk.KeaExam.service.TeamService;
import dk.KeaExam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LeagueService leagueService;



    @PostMapping("/api/search")
    public ModelAndView getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors, Model model) {
        AjaxResponseBody result = new AjaxResponseBody();


        System.out.println("///////");
        System.out.println(search.getUser());

        Team team = teamService.findLoggedInUserTeam(search.getLeagueId());

        Player player = playerRepository.getOne(search.getUsername());

        teamService.addPlayer(search.getLeagueId(), player.getFirstName(), teamService.getListOfPlayers(team).size()+1);

        return new ModelAndView("yay", "draft", model);

    }

}
