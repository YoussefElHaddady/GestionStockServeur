package ma.tc.projects.controller.crud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.controller.CrudController;
import ma.tc.projects.entity.Categorie;
import ma.tc.projects.entity.Produit;
import ma.tc.projects.message.response.ResponseMessage;
import ma.tc.projects.repository.CategorieRepository;
import ma.tc.projects.repository.ProduitRepository;
import ma.tc.projects.service.Imp.ProduitService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/produit")
public class ProduitController extends CrudController<Produit, Long> {

	@Autowired
	private ProduitRepository produitRepo;

	@Autowired
	private CategorieRepository categorieRepo;

	@Autowired
	private ProduitService produitService;

	/*
	 * @Override public ResponseEntity<?> add(@RequestBody Produit entity) {
	 * 
	 * Categorie categorie =
	 * categorieRepo.findByLabel(entity.getCategorie().getLabel()) .orElseThrow(()
	 * -> new RuntimeException("Fail -> Unknown categorie!"));
	 * 
	 * Produit prod = new Produit(entity.getCodeProduit(), entity.getLibelle(),
	 * entity.getDescription(), entity.getDateExp(), entity.getDatePro(),
	 * entity.getPrixDachat(), entity.getPrixUnitaire(), categorie);
	 * 
	 * produitRepo.save(prod);
	 * 
	 * return new ResponseEntity<>(new
	 * ResponseMessage(ResponseMessage.INSERT_SUCCESS), HttpStatus.OK); }
	 */

	@Override
	public ResponseEntity<List<Produit>> addAll(@RequestBody List<Produit> list) {

		List<Produit> prods = new ArrayList<>();

		list.forEach(entity -> {

			Categorie categorie = categorieRepo.findByLabel(entity.getCategorie().getLabel())
					.orElseThrow(() -> new RuntimeException("Fail -> Unknown categorie!"));

//			Produit prod = new Produit(entity.getCodeProduit(), entity.getLibelle(), entity.getDescription(),
//					entity.getDateExp(), entity.getDatePro(), entity.getPrixDachat(), entity.getPrixUnitaire(),
//					categorie);

//			prods.add(prod);
		});

		produitRepo.saveAll(prods);

		return new ResponseEntity<>(prods, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}/{decision}")
	public ResponseEntity<?> delete(@PathVariable long id, @PathVariable String decision) {

		if (produitService.deleteControlled(id, decision))
			return new ResponseEntity<>(new ResponseMessage(ResponseMessage.DELETE_SUCCESS), HttpStatus.OK);

		return new ResponseEntity<>(new ResponseMessage(ResponseMessage.DELETE_FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
