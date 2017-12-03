package dk.KeaExam.repository;

import dk.KeaExam.model.Player;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface PlayerRepository extends DataTablesRepository<Player, Integer> {
}