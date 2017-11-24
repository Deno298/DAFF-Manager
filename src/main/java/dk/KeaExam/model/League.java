package dk.KeaExam.model;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

    public List<MatchSchedule> getMatches() {
        return matches;
    }

    public void addMatches(MatchSchedule matches) {
        this.matches.add(matches);
    }

    @NotNull
    private String password;

    @ManyToMany(mappedBy = "leagues")
    private Set<User> users;

    @NotNull
    private int ownerid;

    @Column
    private LocalDateTime draftDate;

    public LocalDateTime getDraftDate() {
        return draftDate;
    }

    public void setDraftDate(LocalDateTime draftDate) {
        this.draftDate = draftDate;
    }

    public int getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(int ownerid) {
        this.ownerid = ownerid;
    }

    public League() {
    }

    public League(String leagueName, String password) {
        this.leagueName = leagueName;
        this.password = password;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeams(Team team) {
        this.teams.add(team);
    }

    @OneToMany
    @JoinColumn(name = "league_id", referencedColumnName = "league_id")
    private List<Team> teams;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "league_id", referencedColumnName = "league_id")
    private List<MatchSchedule> matches;


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


    public boolean containsUser(User user){
        boolean containsUser = false;
        if(this.getUsers().contains(user)){
            containsUser = true;
        }
        return containsUser;
    }

    @Override
    public String toString() {
        return "League{" +
                "leagueName='" + leagueName + '\'' +
                '}';
    }
}
