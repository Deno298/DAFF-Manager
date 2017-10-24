package dk.KeaExam.controller;

import dk.KeaExam.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepo;

    @RequestMapping("/search")
    public ModelAndView getPlayers() {
        return new ModelAndView("search", "search", playerRepo.findAll());
    }
}