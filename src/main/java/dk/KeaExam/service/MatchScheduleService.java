package dk.KeaExam.service;

import dk.KeaExam.model.League;
import dk.KeaExam.model.Team;

import java.util.List;

/**
 * Match Schedule Service class
 * Containing all match schedule related methods
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
public interface MatchScheduleService {

    void generateMatchSchedule(League league);

    void saveAMatch(String team1, String team2, League league);
}
