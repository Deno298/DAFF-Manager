package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
