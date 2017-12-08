package dk.KeaExam.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public Model DateNotCorrect(Model model) {
        model.addAttribute("errormsg", "Hovsa din dato er vist ikke korrekt");
        model.addAttribute("error", "error");
        return model;
    }

    @Override
    public Model teamNameAlreadyInUse(Model model) {
        model.addAttribute("errormsg", "Dit team navn er allerede i brug");
        model.addAttribute("error", "error");
        return model;
    }

    @Override
    public Model ligaNameAlreadyInUse(Model model) {
        model.addAttribute("errormsg", "Liga navnet eksistere allerede");
        model.addAttribute("error", "error");
        return model;
    }

    @Override
    public Model tooManyLeagues(Model model) {
        model.addAttribute("errormsg", "Du er medlem af for mange leagues");
        model.addAttribute("error", "error");
        return model;
    }
}
