package dk.KeaExam.service;

import dk.KeaExam.model.Player;

import java.util.List;

public interface PlayerService {

    Player findByPlayerName(String firstname);

    List<Player> findAllPlayers();

}
