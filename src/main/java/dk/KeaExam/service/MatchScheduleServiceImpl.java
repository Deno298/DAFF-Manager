package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.MatchSchedule;
import dk.KeaExam.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Class for MatchSchedule Service
 */
@Service
public class MatchScheduleServiceImpl implements MatchScheduleService {

    @Autowired
    private TeamService teamService;

    @Autowired
    LeagueService leagueService;

    /**
     * Generates a match schedule for a league
     * @param league League for which a match schedule needs to be generated
     */
    @Override
    public void generateMatchSchedule(League league) {

            List<Team> teamsInLeague = league.getTeams();

            //In case of odd numbered teams add a "bye" team
            Team bye = teamService.findByTeamName("test");

            if(teamsInLeague.size() % 2 != 0){
                teamsInLeague.add(bye);
            }


            //instantiating variables
            int numRounds = teamsInLeague.size() - 1;
            int halfSize = teamsInLeague.size()/2 ;

            List<Team> teams = new ArrayList<>();

            //add all of listTeam to teams and remove index 0
            teams.addAll(teamsInLeague);
            teams.remove(0);

            int existingTeams = teams.size();

            for(int i = 0; i < numRounds; i++){
                System.out.println("Round: " + i);
                int teamId = i % existingTeams;
                saveAMatch(teams.get(teamId).getTeamName(), teamsInLeague.get(0).getTeamName(), league);
                for(int j = 1; j < halfSize; j++){
                    int firstTeam = (i + j) % existingTeams;
                    int secondTeam = (i+ existingTeams-j) % existingTeams;

                    saveAMatch(teams.get(firstTeam).getTeamName(), teams.get(secondTeam).getTeamName(), league);
                }
            }

        }


    /**
     * Saves a match
     * @param team1 First Team in the match
     * @param team2 Second Team in the match
     * @param league The league in which the match belongs
     */
    public void saveAMatch(String team1, String team2, League league){
        MatchSchedule matchSchedule = new MatchSchedule();
        matchSchedule.setTeam1(team1);
        matchSchedule.setTeam2(team2);
        league.addMatches(matchSchedule);
        leagueService.saveLeague(league);
    }
}
