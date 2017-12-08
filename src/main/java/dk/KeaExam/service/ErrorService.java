package dk.KeaExam.service;

import org.springframework.ui.Model;


public interface ErrorService {

    Model DateNotCorrect(Model model);

    Model teamNameAlreadyInUse(Model model);

    Model ligaNameAlreadyInUse(Model model);

    Model tooManyLeagues(Model model);
}
