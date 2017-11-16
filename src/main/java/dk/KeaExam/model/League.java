package dk.KeaExam.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer league_id;

    @Column(name = "leagueName")
    @NotNull
    private String leagueName;

    @NotNull
    private String password;

    @ManyToMany(mappedBy = "leagues")
    private Set<User> users;

    @NotNull
    private int owner_id;


    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public League() {
    }

    public League(String leagueName, String password) {
        this.leagueName = leagueName;
        this.password = password;
    }

    public Integer getLeague_id() {
        return league_id;
    }

    public void setLeague_id(Integer league_id) {
        this.league_id = league_id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return "League{" +
                "league_id=" + league_id +
                ", leagueName='" + leagueName + '\'' +
                ", password='" + password + '\'' +
                ", users=" + users +
                '}';
    }
}
