package dk.KeaExam.controller;

import dk.KeaExam.model.Customer;
import dk.KeaExam.model.Player;
import dk.KeaExam.model.Response;
import dk.KeaExam.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;



@RestController
public class RestWebController {

	@Autowired
	PlayerRepository playerRepository;

	@RequestMapping(value = "/getallcustomer", method = RequestMethod.GET)
	public Response getResource() {
		Response response = new Response();
		response.setStatus("Done");
		response.setData(playerRepository.findAll());
		return response;
	}

}