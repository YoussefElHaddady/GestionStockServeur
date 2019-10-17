package ma.tc.projects.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.message.response.MonthlyCount;
import ma.tc.projects.message.response.ProductCount;
import ma.tc.projects.service.Imp.StatistiquesService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/statistiques")
public class StatistiquesController {

	@Autowired
	private StatistiquesService statistiquesService;

	@GetMapping("/client/count")
	public int getFournisseursCount() {
		return statistiquesService.getClientsCount();
	}

	@GetMapping("/fournisseur/count")
	public int getOuvriersCount() {
		return statistiquesService.getFournisseursCount();
	}

	@GetMapping("/ouvrier/count")
	public int getClientsCount() {
		return statistiquesService.getOuvriersCount();
	}

	@GetMapping("/produit/count")
	public List<ProductCount> getProduitsCount() {
		return statistiquesService.getProduitsCount();
	}

	@GetMapping("/commande_client/count")
	public List<MonthlyCount> getCommandesClientCount() {
		return statistiquesService.getCommandesClientCount();
	}

}
