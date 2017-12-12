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


/**
 * Rest controller responsible for handling Datatables request.
 * Author Emil Cronfeld
 */
@RestController
public class PlayerRestController {

    @Autowired
    private PlayerService playerService;

    /**
     * Method getting and returning all players.
     * @return Returns a list with all Players.
     */
    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public List<Player> getAllEmployees() {
        return playerService.findAllPlayers();
    }
}