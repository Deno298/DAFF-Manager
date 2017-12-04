package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Player;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private UserService userService;

    @Override
    public void saveLeague(League league) {
        leagueRepository.save(league);
    }

    @Override
    public List<League> findAllLeagues() {
        return leagueRepository.findAll();
    }

    @Override
    public League getOneLeague(Integer leagueId) {
        return leagueRepository.getOne(leagueId);
    }

    @Override
    public List<League> findAllAvailableLeagues() {
        User user = userService.getCurrentUser();
        List<League> leagues = findAllLeagues();

        //Iterate through them, if the user exist remove the league from the list
        for (Iterator<League> it = leagues.iterator(); it.hasNext();){
            League league = it.next();
            if(league.containsUser(user)){
                it.remove();
            }
        }
        return leagues;
    }

    @Override
    public List<User> generateDraftOrder(ArrayList<User> usersInLeague, String draftType) {
        int repeater = 3;
        //list we wants to return
        List<User> draftOrder = new ArrayList<>();

        //Randomizes order of users, users comparable method returns a random value
        Collections.sort(usersInLeague);

        //adds user to final list
        draftOrder.addAll(usersInLeague);

        if(draftType.equals("snake")) {
            Collections.reverse(usersInLeague);
            draftOrder.addAll(usersInLeague);
            repeater = 2;
        }
        for (int i = 0; i < repeater; i++) {
            draftOrder.addAll(draftOrder);
        }

        return draftOrder;
    }
/*
    @Override
    public List<Player> playersInLeague(League league) {

        List<Player> playersInLeague = new ArrayList<>();
        for (Team team: league.getTeams()){
            playersInLeague.addAll(team.getPlayers());
        }

        return playersInLeague;
    }
    */
}

