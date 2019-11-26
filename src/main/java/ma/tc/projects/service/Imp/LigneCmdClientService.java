package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.CommandeClient;
import ma.tc.projects.entity.LigneCmdClient;
import ma.tc.projects.entity.Produit;
import ma.tc.projects.repository.LigneCmdClientRepository;
import ma.tc.projects.service.ICrudService;

@Service
@Primary
public class LigneCmdClientService implements ICrudService<LigneCmdClient, Long> {

	@Autowired
	private LigneCmdClientRepository ligneCmdClientRepo;

	@Override
	public List<LigneCmdClient> getAll() {
		return ligneCmdClientRepo.findAll();
	}

	@Override
	public void add(LigneCmdClient ligneCmdClient) {
		ligneCmdClientRepo.save(ligneCmdClient);
	}

	@Override
	public void update(LigneCmdClient ligneCmdClient) {
		ligneCmdClientRepo.save(ligneCmdClient);
	}

	@Override
	public void delete(Long id_ligneCmdClient) {
		LigneCmdClient a = new LigneCmdClient();
		a.setIdLigneCmdClient(id_ligneCmdClient);
		ligneCmdClientRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<LigneCmdClient> iterable) {
		ligneCmdClientRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<LigneCmdClient> iterable) {
		ligneCmdClientRepo.deleteAll(iterable);
	}

	public boolean deleteByCommandeClient(CommandeClient commandeClient) {
		if (commandeClient == null)
			return false;
		
		if (ligneCmdClientRepo.existsByCommandeClient(commandeClient) == false)
			return false;

		ligneCmdClientRepo.deleteByCmdClientId(commandeClient.getIdCommandeClient());
		return true;
	}

	public boolean deleteByProduit(Produit produit) {
		if (produit == null)
			return false;

		if (ligneCmdClientRepo.existsByProduit(produit) == false)
			return false;

		ligneCmdClientRepo.deleteByProduitId(produit.getIdProduit());
		return true;
	}

}
