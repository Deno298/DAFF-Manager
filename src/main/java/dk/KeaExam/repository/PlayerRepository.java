package dk.KeaExam.repository;

import dk.KeaExam.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Jpa Repository for Player
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
public interface PlayerRepository extends JpaRepository<Player, Integer>{
    /**
     * Find a player in our database based on firstname
     * @param firstName Firstname of the player to be found
     * @return The player found in the database
     */
    Player findByFirstName(String firstName);
}