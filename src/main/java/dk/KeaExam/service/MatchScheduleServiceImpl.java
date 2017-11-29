package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.MatchSchedule;
import dk.KeaExam.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchScheduleServiceImpl implements MatchScheduleService {

    @Autowired
    private TeamService teamService;

    @Autowired
    LeagueService leagueService;

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



    public void saveAMatch(String team1, String team2, League league){
        MatchSchedule matchSchedule = new MatchSchedule();
        matchSchedule.setTeam1(team1);
        matchSchedule.setTeam2(team2);
        league.addMatches(matchSchedule);
        leagueService.saveLeague(league);
    }
}
