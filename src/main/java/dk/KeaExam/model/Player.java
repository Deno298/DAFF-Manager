package dk.KeaExam.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @JsonView(DataTablesOutput.View.class)
    private Integer playerId;

    private Long teamId;

    @JsonView(DataTablesOutput.View.class)
    private String firstName;

    @JsonView(DataTablesOutput.View.class)
    private String lastName;


    @ManyToMany(mappedBy = "players")
    private List<Team> teams = new ArrayList<>();

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeams(Team team) {
        this.teams.add(team);
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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
