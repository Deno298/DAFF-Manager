package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Service Class for User Service
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeagueService leagueService;

    /**
     * Gets the currently logged in user
     * @return User
     */
    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return userRepository.findByUsername(name);
    }

    /**
     * Save a user
     * @param user User to be saved
     */
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Adds a user to a league
     * @param league League of which user needs to be added
     */
    @Override
    public void addUserToLeague(League league) {
        User user = getCurrentUser();
        user.addLeague(league);
        saveUser(user);
        leagueService.saveLeague(league);
    }

    /**
     * Finds a user based on username
     * @param username Username of the user to be found
     * @return User
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Checks if a user is allowed to create leagues
     * @return Boolean
     */
    @Override
    public boolean isUserAllowedToCreateLeague() {
        boolean allowance = true;

        if(getAmountOfUserLeagues() >= 5){
            allowance = false;
        }
        return allowance;
    }

    /**
     * Gets how many leagues a user is a member of
     * @return List of leagues
     */
    @Override
    public int getAmountOfUserLeagues() {

        int amountOfLeagues = 0;
        try{
            amountOfLeagues = getCurrentUser().getLeagues().size();
        }
        catch (NullPointerException e){

        }
        return amountOfLeagues;

    }


}
