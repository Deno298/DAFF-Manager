package dk.KeaExam.service;

import dk.KeaExam.model.Player;

import java.util.List;
/**
 * Player Service class
 * Containing all Player related methods
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
public interface PlayerService {

    Player findByPlayerName(String firstname);

    List<Player> findAllPlayers();

}
