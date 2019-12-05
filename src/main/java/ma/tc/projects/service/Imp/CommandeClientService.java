package ma.tc.projects.service.Imp;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Client;
import ma.tc.projects.entity.CommandeClient;
import ma.tc.projects.entity.Magasin;
import ma.tc.projects.entity.MouvementDeStock;
import ma.tc.projects.entity.Produit;
import ma.tc.projects.entity.ReglementClient;
import ma.tc.projects.enums.TypeDeMvmt;
import ma.tc.projects.message.request.CommandeClientAddingRequest;
import ma.tc.projects.message.response.MonthlyCount;
import ma.tc.projects.repository.CommandeClientRepository;
import ma.tc.projects.service.ICrudService;

@Service
@Primary
public class CommandeClientService implements ICrudService<CommandeClient, Long> {

	@Autowired
	private CommandeClientRepository commandeClientRepo;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ReglementClientService reglementService;

	@Autowired
	private LigneCmdClientService ligneCmdClientService;

	@Autowired
	private MouvementDeStockService mouvementDeStockService;

	@Override
	public List<CommandeClient> getAll() {
		return commandeClientRepo.findAll();
	}

	@Override
	public void add(CommandeClient commandeClient) {
		throw new RuntimeException("not implemented method CommandeClientService.add");
	}

	@Override
	public void update(CommandeClient commandeClient) {
		commandeClientRepo.save(commandeClient);
	}

	@Override
	public void delete(Long id_commandeClient) {
		throw new RuntimeException("not implemented method CommandeClientService.delete");
	}

	@Override
	public void saveAll(Iterable<CommandeClient> iterable) {
		throw new RuntimeException("not implemented method CommandeClientService.saveAll");
	}

	@Override
	public void deleteAll(Iterable<CommandeClient> iterable) {
		iterable.forEach(item -> this.deleteControlled(item.getIdCommandeClient()));
	}

	public void addCommande(CommandeClientAddingRequest commandeReq) {
		long startTime = System.currentTimeMillis();
		long generatedLong = ThreadLocalRandom.current().nextLong();

		// Insert Client if not exist in database
		if (commandeReq.getClient().getIdClient() <= 0) {
			clientService.add(commandeReq.getClient());
		}

		CommandeClient cmd = new CommandeClient("cmd" + generatedLong, commandeReq.getDateCmd(),
				commandeReq.getMontantPaye(), commandeReq.getMontantTotal(), commandeReq.isLivraison());
		cmd.setClient(commandeReq.getClient());

		// Insert CommandeClient object
		commandeClientRepo.save(cmd);

		// Insert the list of ReglementClient objects
		commandeReq.getReglements().forEach(reglement -> reglementService
				.add(new ReglementClient(new Date(), reglement.getMode(), reglement.getMontant(), cmd)));

		// Insert the list of LigneCmdClient & MouvementDeStock objects
		commandeReq.getLignesCmdClient().forEach(ligne -> {
			ligne.setCommandeClient(cmd);
			Produit prodLigne = ligne.getProduit();

			List<MouvementDeStock> originDetails = mouvementDeStockService.getDetailsProduit(prodLigne.getIdProduit());

			prodLigne.getDetails().forEach(detail -> {
				MouvementDeStock mm = originDetails.stream()
						.filter(dt -> dt.getMagasin().getIdMagasin() == detail.getIdMagasin()).findFirst().orElse(null);

				if (mm != null)
					mouvementDeStockService.add(new MouvementDeStock(new Date(), detail.getPrixAchat(),
							detail.getPrixVente(), mm.getQuantite() - detail.getQuantite(), TypeDeMvmt.VENTE, prodLigne,
							new Magasin(detail.getIdMagasin())));
			});

			ligneCmdClientService.add(ligne);
		});

		System.out.println("query result : CommandeClient inserted");
		System.out.println("execution time : " + (System.currentTimeMillis() - startTime) + "ms");
	}

	public List<MonthlyCount> getCount() {
		return commandeClientRepo.commandeClientCountPerMonth();
	}

	public boolean deleteControlled(long idCommandeClient) {
		CommandeClient commandeClient = commandeClientRepo.findById(idCommandeClient).orElse(null);

		if (commandeClient == null)
			return false;

		if (commandeClient.getReglements() != null)
			reglementService.deleteAll(commandeClient.getReglements());

		ligneCmdClientService.deleteByCommandeClient(commandeClient);
		commandeClientRepo.delete(commandeClient);

		return true;
	}

	public List<CommandeClient> getByClient(Client client) {
		return commandeClientRepo.findByClient(client).orElse(null);
	}
}
