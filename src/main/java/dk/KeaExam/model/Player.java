package dk.KeaExam.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer playerId;

    private String firstName;

    private String lastName;

    @ManyToMany(mappedBy = "players")
    private List<Team> teams = new ArrayList<>();

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeams(Team team) {
        this.teams.add(team);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
