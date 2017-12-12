package dk.KeaExam.repository;

import dk.KeaExam.model.MatchSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jpa Repository for MatchSchedule
 * Author Emil Cronfeld
 * Author Dennis Fagerstrøm Petersen
 */
public interface MatchScheduleRepository extends JpaRepository<MatchSchedule, Integer> {


}
