package dk.KeaExam.controller;


import java.util.List;

import dk.KeaExam.model.Player;
import dk.KeaExam.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRestController {

   @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public List<Player> getAllEmployees() {
        List<Player> hey = playerRepository.findAll();
        return hey;
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Player getEmployeeById(@PathVariable("id") long id) {
        return null;
    }

}