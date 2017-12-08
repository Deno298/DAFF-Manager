package dk.KeaExam.controller;


import java.util.List;

import dk.KeaExam.model.Player;
import dk.KeaExam.repository.PlayerRepository;
import dk.KeaExam.service.LeagueService;
import dk.KeaExam.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRestController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private LeagueService leagueService;

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public List<Player> getAllEmployees() {
        return playerService.findAllPlayers();
    }
}