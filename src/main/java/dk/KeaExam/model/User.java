package dk.KeaExam.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Team Entity
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Entity
public class User implements Comparable<User>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_LEAGUE",
    joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name = "league_id"))
    private Set<League> leagues;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<Team> teams;


    public List<Team> getTeams() {
        return teams;
    }

    public void addTeams(Team teams) {
        this.teams.add(teams);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<League> getLeagues() {
        return leagues;
    }

    public void addLeague(League league) {
        this.leagues.add(league);
    }

    /**
     * Gives a user a random compare to
     * @param user User to be compared
     * @return Returns completely random integer in the integers range
     */
    @Override
    public int compareTo(User user){
        Random rng = new Random();
        return rng.nextInt() * 2 - 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}


