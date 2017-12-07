package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    //finds logged in user
    User getCurrentUser();

    void saveUser(User user);

    void addUserToLeague(League league);

    User findByUsername(String username);

    boolean isUserAllowedToCreateLeague();

    int getAmountOfUserLeagues();

}
