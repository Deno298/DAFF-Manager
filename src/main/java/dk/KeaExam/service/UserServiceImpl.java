package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private TeamService teamService;

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return userRepository.findByUsername(name);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void addUserToLeague(League league, String teamName, User user) {



    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isUserAllowedToCreateLeague() {
        boolean allowance = true;

        if(getAmountOfUserLeagues() >= 5){
            allowance = false;
        }
        return allowance;
    }

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
