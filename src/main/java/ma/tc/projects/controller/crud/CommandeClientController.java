package ma.tc.projects.controller.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.controller.CrudController;
import ma.tc.projects.entity.CommandeClient;
import ma.tc.projects.message.request.CommandeClientAddingRequest;
import ma.tc.projects.message.response.ResponseMessage;
import ma.tc.projects.service.Imp.CommandeClientService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/commande_client")
public class CommandeClientController extends CrudController<CommandeClient, Long> {

	@Autowired
	CommandeClientService commandeClientService;

	@Override
	public ResponseEntity<?> add(CommandeClient entity) {
		return new ResponseEntity<>(new ResponseMessage(ResponseMessage.NOT_IMPLEMENTED_FEATURE),
				HttpStatus.EXPECTATION_FAILED);
	}

	@Override
	public ResponseEntity<List<CommandeClient>> addAll(List<CommandeClient> list) {
		return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping("/add_commande")
	public ResponseEntity<?> addCmd(@RequestBody CommandeClientAddingRequest commande) {
		commandeClientService.addCommande(commande);
		return new ResponseEntity<>(new ResponseMessage(ResponseMessage.INSERT_SUCCESS), HttpStatus.ACCEPTED);
	}

}
