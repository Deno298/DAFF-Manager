package dk.KeaExam.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * Service Class for Error Service
 * Author Emil Cronfeld
 * Author Dennis Fargerstrøm Petersen
 */
@Service
public class ErrorServiceImpl implements ErrorService {

    /**
     * Adds an error message to a model
     * @param model Model which neds to be added an errormessage
     * @return The model
     */
    @Override
    public Model DateNotCorrect(Model model) {
        model.addAttribute("errormsg", "Hovsa din dato er vist ikke korrekt");
        model.addAttribute("error", "error");
        return model;
    }

    /**
     * Adds an error message to a model
     * @param model Model which neds to be added an errormessage
     * @return The model
     */
    @Override
    public Model teamNameAlreadyInUse(Model model) {
        model.addAttribute("errormsg", "Dit team navn er allerede i brug");
        model.addAttribute("error", "error");
        return model;
    }

    /**
     * Adds an error message to a model
     * @param model Model which neds to be added an errormessage
     * @return The model
     */
    @Override
    public Model ligaNameAlreadyInUse(Model model) {
        model.addAttribute("errormsg", "Liga navnet eksistere allerede");
        model.addAttribute("error", "error");
        return model;
    }

    /**
     * Adds an error message to a model
     * @param model Model which neds to be added an errormessage
     * @return The model
     */
    @Override
    public Model tooManyLeagues(Model model) {
        model.addAttribute("errormsg", "Du er medlem af for mange leagues");
        model.addAttribute("error", "error");
        return model;
    }
}
