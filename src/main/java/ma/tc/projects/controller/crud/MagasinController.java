package ma.tc.projects.controller.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.controller.CrudController;
import ma.tc.projects.entity.Magasin;
import ma.tc.projects.message.response.ResponseMessage;
import ma.tc.projects.service.ICrudService;
import ma.tc.projects.service.Imp.MagasinService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/magasin")
public class MagasinController extends CrudController<Magasin, Long>{

	@Autowired
	private MagasinService magasinService;
	
	@PostMapping("/by_username")
	public Magasin getByUsername(@RequestBody String username) {
		return magasinService.getByUsername(username)
	            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Magasin not find."));
	}
}
