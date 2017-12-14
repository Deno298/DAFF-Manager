package dk.KeaExam.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Player Entity
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer playerId;

    private String firstName;

    private String lastName;

    private String position;

    private int throwingYards;

    private int thrownTouchdowns;

    private int thrownInterceptions;

    private int rushingYards;

    private int touchdowns;

    private int fumbles;

    private int receptions;

    private int receivingYards;

    private int sacks;

    private int forcedFumbles;

    private int fumbleRecoveries;

    private int interceptions;

    private int tackles;

    private double points;



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

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getThrowingYards() {
        return throwingYards;
    }

    public void setThrowingYards(int throwingYards) {
        this.throwingYards = throwingYards;
    }

    public int getThrownTouchdowns() {
        return thrownTouchdowns;
    }

    public void setThrownTouchdowns(int thrownTouchdowns) {
        this.thrownTouchdowns = thrownTouchdowns;
    }

    public int getThrownInterceptions() {
        return thrownInterceptions;
    }

    public void setThrownInterceptions(int thrownInterceptions) {
        this.thrownInterceptions = thrownInterceptions;
    }

    public int getRushingYards() {
        return rushingYards;
    }

    public void setRushingYards(int rushingYards) {
        this.rushingYards = rushingYards;
    }

    public int getTouchdowns() {
        return touchdowns;
    }

    public void setTouchdowns(int touchdowns) {
        this.touchdowns = touchdowns;
    }

    public int getFumbles() {
        return fumbles;
    }

    public void setFumbles(int fumbles) {
        this.fumbles = fumbles;
    }

    public int getReceptions() {
        return receptions;
    }

    public void setReceptions(int receptions) {
        this.receptions = receptions;
    }

    public int getReceivingYards() {
        return receivingYards;
    }

    public void setReceivingYards(int receivingYards) {
        this.receivingYards = receivingYards;
    }

    public int getSacks() {
        return sacks;
    }

    public void setSacks(int sacks) {
        this.sacks = sacks;
    }

    public int getForcedFumbles() {
        return forcedFumbles;
    }

    public void setForcedFumbles(int forcedFumbles) {
        this.forcedFumbles = forcedFumbles;
    }

    public int getFumbleRecoveries() {
        return fumbleRecoveries;
    }

    public void setFumbleRecoveries(int fumbleRecoveries) {
        this.fumbleRecoveries = fumbleRecoveries;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public void setInterceptions(int interceptions) {
        this.interceptions = interceptions;
    }

    public int getTackles() {
        return tackles;
    }

    public void setTackles(int tackles) {
        this.tackles = tackles;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Player{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
