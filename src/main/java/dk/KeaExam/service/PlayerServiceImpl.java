package dk.KeaExam.service;

import dk.KeaExam.model.Player;
import dk.KeaExam.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Class for Player Service
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Finds a player by player name
     * @param firstname Firstname of player to be found
     * @return Player
     */
    @Override
    public Player findByPlayerName(String firstname) {
        return playerRepository.findByFirstName(firstname);
    }

    /**
     * Find all players
     * @return A list of all players
     */
    @Override
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }
}
