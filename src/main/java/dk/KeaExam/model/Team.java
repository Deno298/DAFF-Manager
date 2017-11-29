package dk.KeaExam.model;

import dk.KeaExam.model.Player;
import dk.KeaExam.model.User;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team implements Comparable<Team> {

    @Id
    @NotNull
    private String teamName;

    private Integer id;

    private Integer leagueid;

    private int wins;

    private int loses;

    private int draws;

    private int winPercentage;

    private int points;

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(int winPercentage) {
        this.winPercentage = winPercentage;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Integer getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(Integer leagueid) {
        this.leagueid = leagueid;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Teams", joinColumns = @JoinColumn(name="team_name"), inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Player> players = new ArrayList<>();

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player players) {
        this.players.add(players);
    }

    @Override
    public int compareTo(Team team){
        return team.getPoints() - this.points;
    }

    @Override
    public String toString() {
        return teamName;
    }
}
