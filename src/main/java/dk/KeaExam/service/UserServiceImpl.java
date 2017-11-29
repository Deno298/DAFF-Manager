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
    public User addUserToLeague(League league, String teamName, User user) {

            //retrieve the user from the database so we can add him to the league
            user = getCurrentUser();
            user.addLeague(league);

            //creates and adds team
            Team team = new Team();
            team.setTeamName(teamName);
            league.addTeams(team);
            user.addTeams(team);

            //saving
            teamService.saveTeam(team);
            saveUser(user);
            return user;
    }
}
