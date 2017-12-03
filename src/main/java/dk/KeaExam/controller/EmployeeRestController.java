package dk.KeaExam.controller;


import java.util.List;

import dk.KeaExam.model.Player;
import dk.KeaExam.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class EmployeeRestController {

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public List<Player> getAllEmployees() {

        return null;
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Player getEmployeeById(@PathVariable("id") long id) {
        return null;
    }

    @RequestMapping(value = "/data/users", method = RequestMethod.GET)
    public DataTablesOutput<Player> getUsers(@Valid DataTablesInput input) {
        return playerRepository.findAll(input);
    }
}