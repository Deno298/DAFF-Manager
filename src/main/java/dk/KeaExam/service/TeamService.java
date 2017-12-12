package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Player;
import dk.KeaExam.model.Team;

import java.util.List;



/**
 * Team Service class
 * Containing all Team related methods
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
public interface TeamService {
    Team findByTeamName(String teamName);

    void saveTeam(Team team);

    Team findLoggedInUserTeam(Integer leagueId);

    List<Team> getAllTeamsInLeague(League league);

    void addPlayer(int leagueid, String addPlayer, int round);

    List<Player> getListOfPlayers(Team team);

    void addTeamToLeague(String teamName, League league);

}
