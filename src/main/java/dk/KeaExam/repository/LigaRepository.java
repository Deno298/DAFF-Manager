package dk.KeaExam.repository;

import dk.KeaExam.model.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigaRepository extends JpaRepository<Liga, Integer> {

}