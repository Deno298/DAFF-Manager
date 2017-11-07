package dk.KeaExam.controller;

import dk.KeaExam.model.Liga;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OpretLigaController {
    private LigaRepository ligaRepository;

    @Autowired
    public OpretLigaController(LigaRepository ligaRepository){
        this.ligaRepository=ligaRepository;
    }

    @GetMapping("/opretliga")
    public String greetingForm(Model model) {
        model.addAttribute("liga", new Liga());
        return "opretliga";
    }

    @PostMapping("/opretliga")
    public String opretLiga(Liga liga){
        ligaRepository.save(liga);
        return "landingpage";
    }

}
