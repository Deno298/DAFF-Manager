package dk.KeaExam.repository;

import dk.KeaExam.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueRepository extends JpaRepository<League, Integer> {
    League findByLeagueName(String leagueName);
}
