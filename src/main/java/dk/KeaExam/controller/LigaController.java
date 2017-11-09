package dk.KeaExam.controller;

import dk.KeaExam.model.Liga;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LigaRepository;
import dk.KeaExam.repository.PlayerRepository;
import dk.KeaExam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;

@Controller
public class LigaController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LigaRepository ligaRepository;

    @RequestMapping("/ligaoverview")
    public ModelAndView getLiga() {
        return new ModelAndView("ligaoverview", "ligaoverview", ligaRepository.findAll());
    }

    @PostMapping("/ligaoverview")
    public String userDelete(@ModelAttribute User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        System.out.println(name);
        Liga liga = ligaRepository.getOne(1);
        User userExists = (userRepository.findByUsername(name));
        userExists.tilføjLigaer(liga);
        userRepository.save(userExists);



        return "landingpage";
    }
}