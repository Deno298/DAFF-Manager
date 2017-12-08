package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Player;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public interface LeagueService {
    Model createLeague(League league, String year, String month, String dayOfMonth, String hour, String minute, String teamName, Model model);

    List<League> findAllLeagues();

    League getOneLeague(Integer LeagueId);

    List<League> findAllAvailableLeagues();

    List<User> generateDraftOrder(int leagueid);
    /*
    List<Player> playersInLeague(League league);

    List<Player> availablePlayersInLeague(League league);
    */
    boolean isDateValid(String date);

    void saveLeague(League league);

    League getOneLeague(String leagueName);

    Model joinLeague(Integer leagueId, String password, String teamName, Model model);

    List<Team> getStandings(League league);
}
