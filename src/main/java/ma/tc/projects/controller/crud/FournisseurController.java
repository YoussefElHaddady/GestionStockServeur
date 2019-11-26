package ma.tc.projects.controller.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.controller.CrudController;
import ma.tc.projects.entity.Fournisseur;
import ma.tc.projects.message.response.ResponseMessage;
import ma.tc.projects.service.Imp.FournisseurService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/fournisseur")
public class FournisseurController extends CrudController<Fournisseur, Long>{
	
	@Autowired
	private FournisseurService fournisseurService;

	
	@DeleteMapping("/{id}/{decision}")
	public ResponseEntity<?> delete(@PathVariable long id, @PathVariable String decision) {

		if (fournisseurService.deleteControlled(id, decision))
			return new ResponseEntity<>(new ResponseMessage(ResponseMessage.DELETE_SUCCESS), HttpStatus.OK);

		return new ResponseEntity<>(new ResponseMessage(ResponseMessage.DELETE_FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
