package ma.tc.projects.controller.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.controller.CrudController;
import ma.tc.projects.entity.Categorie;
import ma.tc.projects.entity.Magasin;
import ma.tc.projects.message.response.ResponseMessage;
import ma.tc.projects.service.Imp.CategorieService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/categorie")
public class CategorieController extends CrudController<Categorie, Long> {

	@Autowired
	CategorieService categorieService;

	@DeleteMapping("/{id}/{decision}")
	public ResponseEntity<?> delete(@PathVariable long id, @PathVariable String decision) {

		if (categorieService.deleteControlled(id, decision))
			return new ResponseEntity<>(new ResponseMessage(ResponseMessage.DELETE_SUCCESS), HttpStatus.OK);

		return new ResponseEntity<>(new ResponseMessage(ResponseMessage.DELETE_FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/by_mag")
	public List<Categorie> getAllByMagasin(@RequestBody Magasin magasin) {

		List<Categorie> cats = categorieService.getAllByMagasin(magasin.getIdMagasin());
		return cats;
	}

}
