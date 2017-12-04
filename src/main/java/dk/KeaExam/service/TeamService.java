package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Team;

import java.util.List;

public interface TeamService {
    Team findByTeamName(String teamName);

    void saveTeam(Team team);

    Team findLoggedInUserTeam(Integer leagueId);

    List<Team> getAllTeamsInLeague(League league);

    void addPlayer(Team team);
}
