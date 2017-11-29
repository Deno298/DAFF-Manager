package dk.KeaExam.controller;

import dk.KeaExam.model.*;
import dk.KeaExam.repository.LeagueRepository;
import dk.KeaExam.repository.PlayerRepository;
import dk.KeaExam.repository.TeamRepository;
import dk.KeaExam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;



@RestController
public class RestWebController {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	LeagueRepository leagueRepository;

	@RequestMapping(value = "/getallcustomer", method = RequestMethod.GET)
	public Response getResource() {
		Response response = new Response();
		response.setStatus("Done");
		response.setData(playerRepository.findAll());
		return response;
	}

	@RequestMapping(value= "/draftplayer", method = RequestMethod.POST)
	public Response postPlayer(@RequestBody Player player){
		System.out.println("We in");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		User user = userRepository.findByUsername(name);

		League league = leagueRepository.getOne(1);
		Team team = new Team();
		for(int i = 0; i < league.getTeams().size(); i++){
			if(user.getId() == league.getTeams().get(i).getId()){
				team = league.getTeams().get(i);
				break;
			}
		}



		playerRepository.getOne(player.getPlayerId());

		team.addPlayer(player);

		teamRepository.save(team);




		Response response = new Response();
		response.setStatus("We did it");
		response.setData(playerRepository.findAll());

		return response;
	}
}