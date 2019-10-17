package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.message.response.MonthlyCount;
import ma.tc.projects.message.response.ProductCount;

@Service
@Primary
public class StatistiquesService {

	@Autowired
	private ClientService clientService;
	@Autowired
	private FournisseurService fournisseurService;
	@Autowired
	private OuvrierService ouvrierService;
	@Autowired
	private ProduitService produitService;
	@Autowired
	private CommandeClientService commadeClientService;

	public int getClientsCount() {
		return clientService.getCount();
	}

	public int getFournisseursCount() {
		return fournisseurService.getCount();
	}

	public int getOuvriersCount() {
		return ouvrierService.getCount();
	}

	public List<ProductCount> getProduitsCount() {
		return produitService.getCount();
	}

	public List<MonthlyCount> getCommandesClientCount() {
		return commadeClientService.getCount();
	}

}
