package dk.KeaExam.service;

import org.springframework.ui.Model;

/**
 * Error Service Class
 * Contains all error message related methods
 */
public interface ErrorService {

    Model DateNotCorrect(Model model);

    Model teamNameAlreadyInUse(Model model);

    Model ligaNameAlreadyInUse(Model model);

    Model tooManyLeagues(Model model);
}
