package dk.KeaExam.repository;

import dk.KeaExam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * Jpa Repository for User
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Finds a user based on username
     * @param username Username of the user wished to be found
     * @return The user found in the database
     */
    User findByUsername(String username);
}
