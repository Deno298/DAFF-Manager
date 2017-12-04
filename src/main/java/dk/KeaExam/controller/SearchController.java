package dk.KeaExam.controller;


import dk.KeaExam.model.*;
import dk.KeaExam.repository.PlayerRepository;
import dk.KeaExam.service.TeamService;
import dk.KeaExam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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




    @PostMapping("/api/search")
    public List<Player> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {
        System.out.println("hej");
        AjaxResponseBody result = new AjaxResponseBody();
        System.out.println(search.getUsername());
        System.out.println(search.getLeagueId());

        Team team = teamService.findLoggedInUserTeam(search.getLeagueId());
        System.out.println(team);
        team.addPlayer(playerRepository.getOne(search.getUsername()));
        teamService.saveTeam(team);
        //If error, just return a 400 bad request, along with the error message
        return playerRepository.findAll();
    }

}
