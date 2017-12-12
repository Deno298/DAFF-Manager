package dk.KeaExam.repository;

import dk.KeaExam.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Jpa repository for League
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
@Repository
public interface LeagueRepository extends JpaRepository<League, Integer> {
    /**
     * Finds a league in our database based on the leaguename
     * @param leagueName Leaguename of the league wished to be found
     * @return a League
     */
    League findByLeagueName(String leagueName);
}
