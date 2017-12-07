package dk.KeaExam.service;

import dk.KeaExam.model.Player;
import dk.KeaExam.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player findByPlayerName(String firstname) {
        return playerRepository.findByFirstName(firstname);
    }

    @Override
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }
}
