package ma.tc.projects.controller.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.controller.CrudController;
import ma.tc.projects.entity.Categorie;
import ma.tc.projects.entity.Magasin;
import ma.tc.projects.service.Imp.CategorieService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/categorie")
public class CategorieController extends CrudController<Categorie, Long> {

	@Autowired
	CategorieService categorieService;

	@PostMapping("/by_mag")
	public List<Categorie> getAllByMagasin(@RequestBody Magasin magasin) {
		
		return categorieService.getAllByMagasin(magasin.getIdMagasin());
	}

}
