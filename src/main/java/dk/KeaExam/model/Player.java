package dk.KeaExam.model;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "playerID")
    private Long playerId;
    @Column(name = "teamID")
    private Long teamId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "off_pos")
    private String offPos;
    @Column(name = "def_pos")
    private String defPos;
    @Column(name = "throwing")
    private Integer throwing;
    @Column(name = "precision")
    private Integer precision;
    @Column(name = "vision")
    private Integer vision;
    @Column(name = "speed")
    private Integer speed;
    @Column(name = "acceleration")
    private Integer acceleration;
    @Column(name = "route_running")
    private Integer routeRunning;
    @Column(name = "catching")
    private Integer catching;
    @Column(name = "tackling")
    private Integer tackling;
    @Column(name = "blitzing")
    private Integer blitzing;
    @Column(name = "man_coverage")
    private Integer manCoverage;
    @Column(name = "zone_coverage")
    private Integer zoneCoverage;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

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

    public String getOffPos() {
        return offPos;
    }

    public void setOffPos(String offPos) {
        this.offPos = offPos;
    }

    public String getDefPos() {
        return defPos;
    }

    public void setDefPos(String defPos) {
        this.defPos = defPos;
    }

    public Integer getThrowing() {
        return throwing;
    }

    public void setThrowing(Integer throwing) {
        this.throwing = throwing;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getVision() {
        return vision;
    }

    public void setVision(Integer vision) {
        this.vision = vision;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Integer acceleration) {
        this.acceleration = acceleration;
    }

    public Integer getRouteRunning() {
        return routeRunning;
    }

    public void setRouteRunning(Integer routeRunning) {
        this.routeRunning = routeRunning;
    }

    public Integer getCatching() {
        return catching;
    }

    public void setCatching(Integer catching) {
        this.catching = catching;
    }

    public Integer getTackling() {
        return tackling;
    }

    public void setTackling(Integer tackling) {
        this.tackling = tackling;
    }

    public Integer getBlitzing() {
        return blitzing;
    }

    public void setBlitzing(Integer blitzing) {
        this.blitzing = blitzing;
    }

    public Integer getManCoverage() {
        return manCoverage;
    }

    public void setManCoverage(Integer manCoverage) {
        this.manCoverage = manCoverage;
    }

    public Integer getZoneCoverage() {
        return zoneCoverage;
    }

    public void setZoneCoverage(Integer zoneCoverage) {
        this.zoneCoverage = zoneCoverage;
    }
}
