package ma.tc.projects.controller.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.controller.CrudController;
import ma.tc.projects.entity.MouvementDeStock;
import ma.tc.projects.message.request.ProdQuantity;
import ma.tc.projects.service.Imp.MouvementDeStockService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/mouvement_de_stock")
public class MouvementDeStockController extends CrudController<MouvementDeStock, Long>{

	@Autowired
	public MouvementDeStockService msService;	
	
	@PostMapping("/by_mag_prod")
	public List<Integer> getQuantite(@RequestBody ProdQuantity prodQuantityReq) {
		return msService.getQuantiteByMagProd(prodQuantityReq.getIdMagasin(), prodQuantityReq.getIdCategorie());
	}
	
	@PostMapping("/first_category")
	public List<Integer> getFirstQuantites(@RequestBody long idMagasin) {
		return msService.getQuantitiesForTheFirstCategory(idMagasin);
	}
}
