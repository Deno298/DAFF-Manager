package dk.KeaExam.repository;

import dk.KeaExam.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Jpa Repository for Team
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
public interface TeamRepository extends JpaRepository<Team, String> {
        /**
         * Find a Team in our database based on teamname
         * @param teamName Teamname of the team wished to be found
         * @return The Team found in the database
         */
        Team findByTeamName(String teamName);
}
