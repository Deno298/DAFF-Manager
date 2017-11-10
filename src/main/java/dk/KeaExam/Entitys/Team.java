package dk.KeaExam.Entitys;

import dk.KeaExam.model.Liga;
import dk.KeaExam.model.Player;
import dk.KeaExam.model.User;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @NotNull
    private String teamName;

    @NotNull
    private User owner;

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


}
