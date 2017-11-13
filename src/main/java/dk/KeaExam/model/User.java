package dk.KeaExam.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_LIGA",
    joinColumns = @JoinColumn(name="id"),
    inverseJoinColumns = @JoinColumn(name = "liga_id"))
    private List<Liga> ligaer = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "id")
    private List<Team> teams;


    public List<Team> getTeams() {
        return teams;
    }

    public void addTeams(Team teams) {
        this.teams.add(teams);
    }

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public List<Liga> getLigaer() {
        return ligaer;
    }

    public void tilføjLigaer(Liga liga) {
        this.ligaer.add(liga);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", ligaer=" + ligaer +
                '}';
    }
}


