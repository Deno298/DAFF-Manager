package dk.KeaExam.model;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is the League Entity responsible for mapping the league object in our database.
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Entity
public class League {

    /**
     * Attribute
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer leagueid;

    /**
     * Attribute
     */
    @Column(name = "leagueName")
    @NotNull
    private String leagueName;

    /**
     * Attribute
     */
    @NotNull
    private String password;

    /**
     * Attribute
     */
    @ManyToMany(mappedBy = "leagues")
    private Set<User> users;

    /**
     * Attribute
     */
    @NotNull
    private int ownerid;

    /**
     * Attribute
     */
    @Column
    private LocalDateTime draftDate;

    /**
     * Attribute
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "leagueid", referencedColumnName = "leagueid")
    private List<Team> teams;

    /**
     * Attribute
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "leagueid", referencedColumnName = "leagueid")
    private List<MatchSchedule> matches;

    /**
     * No argument constructor
     */
    public League() {
    }

    /**
     * Constructor
     * @param leagueName Leaguename to be set
     * @param password Password to be set
     */
    public League(String leagueName, String password) {
        this.leagueName = leagueName;
        this.password = password;
    }

    public Integer getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(Integer leagueid) {
        this.leagueid = leagueid;
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

    /**
     * Adds a user to the leagues user list
     * @param user User to be added to league
     */
    public void addUsers(User user) {
        if(user != null){
            if(users == null){
                users = new HashSet<>();
            }
            users.add(user);
        }
    }

    public List<MatchSchedule> getMatches() {
        return matches;
    }

    /**
     * Adds a match to the leagues match schedule
     * @param match Match to be added
     */
    public void addMatches(MatchSchedule match) {
            if(match != null){
                if(matches == null){
                    matches = new ArrayList<>();
                }
                matches.add(match);
            }
    }

    public List<Team> getTeams() {
        return teams;
    }

    /**
     * Adds a team to the leagues team list
     * @param team Team to be added
     */
    public void addTeams(Team team) {
        if(team != null){
            if(teams == null){
                teams = new ArrayList<Team>();
            }
            teams.add(team);
        }
    }


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

    /**
     * Checks if a user is contained in the league
     * @param user User to be checked
     * @return A boolean, true if it contains the user.
     */
    public boolean containsUser(User user){
        boolean containsUser = false;
        if(this.getUsers().contains(user)){
            containsUser = true;
        }
        return containsUser;
    }

    /**
     * Classic toString
     * @return leaguename
     */
    @Override
    public String toString() {
        return "League{" +
                "leagueName='" + leagueName + '\'' +
                '}';
    }
}
