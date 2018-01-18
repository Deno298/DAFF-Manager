package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Player;
import dk.KeaExam.model.Team;
import dk.KeaExam.model.User;
import dk.KeaExam.repository.PlayerRepository;
import dk.KeaExam.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Service Class for Team Service
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LeagueService leagueService;

    /**
     * Finds a team by teamname
     * @param teamName Teamname of team to be found
     * @return Team
     */
    @Override
    public Team findByTeamName(String teamName) {
        return teamRepository.findByTeamName(teamName);
    }

    /**
     * Saves a team
     * @param team Team which needs to be saved
     */
    @Override
    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    /**
     * Find the logged in users team
     * @param leagueId The league id of the league the team belongs to
     * @return Team
     */
    @Override
    public Team findLoggedInUserTeam(Integer leagueId) {
        //Finder brugeren
        User user = userService.getCurrentUser();
        //Finder brugerens hold i ligaen
        for (Team team : user.getTeams()) {
            if (team.getLeagueid() == leagueId) {
                return team;
            }
        }
        return null;
    }

    /**
     * Get all teams in a league
     * @param league League for which teams needs to be returned
     * @return List of teams in that league
     */
    @Override
    public List<Team> getAllTeamsInLeague(League league) {
        return league.getTeams();
    }

    /**
     * Adds a player to a team
     * @param leagueid Leagueid of league in which the team is located
     * @param addPlayer Player to be added
     * @param round Draft round the user is currently in
     */
    @Override
    public void addPlayer(int leagueid, String addPlayer, int round) {
        Team team = findLoggedInUserTeam(1);
        switch (round){
            case 1: team.setPlayer1(addPlayer);
                    break;
            case 2: team.setPlayer2(addPlayer);
                    break;
            case 3: team.setPlayer3(addPlayer);
                    break;
            case 4: team.setPlayer4(addPlayer);
                    break;
            case 5: team.setPlayer5(addPlayer);
                    break;
            case 6: team.setPlayer6(addPlayer);
                    break;
            case 7: team.setPlayer7(addPlayer);
                    break;
            case 8: team.setPlayer8(addPlayer);
                    break;
            default:
                System.out.println("round over");
                break;
        }
        saveTeam(team);
    }

    /**
     * Get a list of all players in a team
     * @param team Team for which to get players
     * @return A list of players
     */
    @Override
    public List<Player> getListOfPlayers(Team team) {
        List<Player> players = new ArrayList<>();
        for (String player : team.getPlayers()) {
            if (player != null && !player.isEmpty()) {
                players.add(playerService.findByPlayerName(player));
            } else {
                break;
            }
        }
        return players;
    }

    /**
     * Adds a team to a league
     * @param teamName Teamname of team to be added
     * @param league League for which the team needs to be added
     */
    @Override
    public void addTeamToLeague(String teamName, League league) {

        Team team = new Team();
        team.setTeamName(teamName);
        league.addTeams(team);
        leagueService.saveLeague(league);
        saveTeam(team);
    }
}

