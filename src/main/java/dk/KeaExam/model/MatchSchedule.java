package dk.KeaExam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * MatchSchedule Entity
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Entity
public class MatchSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer match_id;

    private Integer league_id;

    @NotNull
    private String team1;

    @NotNull
    private String team2;

    private int team1points;

    private int team2points;

    public Integer getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Integer match_id) {
        this.match_id = match_id;
    }

    public Integer getLeague_id() {
        return league_id;
    }

    public void setLeague_id(Integer league_id) {
        this.league_id = league_id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getTeam1points() {
        return team1points;
    }

    public void setTeam1points(int team1points) {
        this.team1points = team1points;
    }

    public int getTeam2points() {
        return team2points;
    }

    public void setTeam2points(int team2points) {
        this.team2points = team2points;
    }
}
