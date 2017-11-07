package dk.KeaExam.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Liga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long liga_id;

    @NotNull
    private String liganame;

    @NotNull
    private String password;

    @ManyToMany(mappedBy = "ligaer")
    private List<User> users = new ArrayList<>();

    public Liga() {
    }

    public Liga(String liganame, String password) {
        this.liganame = liganame;
        this.password = password;
    }

    public long getLiga_id() {
        return liga_id;
    }

    public void setLiga_id(long liga_id) {
        this.liga_id = liga_id;
    }

    public String getLiganame() {
        return liganame;
    }

    public void setLiganame(String liganame) {
        this.liganame = liganame;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        this.users.add(user);
    }
}
