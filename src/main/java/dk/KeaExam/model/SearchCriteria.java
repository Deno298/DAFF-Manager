package dk.KeaExam.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Restful object used to transfer info from javascript to controller
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
public class SearchCriteria {

    Integer username;

    Integer leagueId;

    List<User> user = new ArrayList<>();

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getUsername() {
        return username;
    }

    public void setUsername(Integer username) {
        this.username = username;
    }
}