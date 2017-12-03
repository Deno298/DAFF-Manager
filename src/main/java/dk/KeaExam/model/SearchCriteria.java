package dk.KeaExam.model;

import org.hibernate.validator.constraints.NotBlank;

public class SearchCriteria {

    Long username;

    Integer leagueId;

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }
}