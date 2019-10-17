package ma.tc.projects.service.Imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.CommandeClient;
import ma.tc.projects.entity.LigneCmdClient;
import ma.tc.projects.entity.Magasin;
import ma.tc.projects.entity.MouvementDeStock;
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
	private MagasinService magasinService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ReglementClientService reglementService;

	@Autowired
	private LigneCmdClientService ligneCmdClientService;

	@Autowired
	private MouvementDeStockService mouvementDeStockService;

	private int i;

	@Override
	public List<CommandeClient> getAll() {
		return commandeClientRepo.findAll();
	}

	@Override
	public void add(CommandeClient commandeClient) {
		// commandeClientRepo.save(commandeClient);
	}

	@Override
	public void update(CommandeClient commandeClient) {
		commandeClientRepo.save(commandeClient);
	}

	@Override
	public void delete(Long id_commandeClient) {
		CommandeClient a = new CommandeClient();
		a.setIdCommandeClient(id_commandeClient);
		commandeClientRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<CommandeClient> iterable) {
		// commandeClientRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<CommandeClient> iterable) {
		commandeClientRepo.deleteAll(iterable);
	}

	public void addCommande(CommandeClientAddingRequest commandeReq) {
		long startTime = System.currentTimeMillis();
		long generatedLong = ThreadLocalRandom.current().nextLong();
		Magasin magasin = magasinService.getById(commandeReq.getIdMagasin());

		// Insert Client if not exist in database
		if (commandeReq.getClient().getIdClient() <= 0) {
			clientService.add(commandeReq.getClient());
		}

		CommandeClient cmd = new CommandeClient("cmd" + generatedLong, commandeReq.getDateCmd(),
				commandeReq.getMontantPaye(), commandeReq.getMontantTotal(), commandeReq.isLivraison());
		cmd.setClient(commandeReq.getClient());

		// Insert CommandeClient object
		commandeClientRepo.save(cmd);

		// searching for CommandeClient with its code cuz we'll need this command with
		// its id (we don't have it until now)
//		cmd = commandeClientRepo.findByCodeCmd(cmd.getCodeCmd()).orElseThrow(() -> new RuntimeException(
//				"Fail! -> Cause: commande client not find in CommandeClientService.addCommande()."));

		// Insert commandeClient's ReglementClient list objects
		commandeReq.getReglements().forEach(reglement -> reglementService
				.add(new ReglementClient(new Date(), reglement.getMode(), reglement.getMontant(), cmd)));

		// Insert commandeClient's LigneCmdClient list objects
		List<LigneCmdClient> lignesCmd = new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		commandeReq.getLignesCmdClient().forEach(ligne -> {
			ligne.setCommandeClient(cmd);
			ids.add(ligne.getProduit().getIdProduit());
			lignesCmd.add(ligne);
		});
		ligneCmdClientService.saveAll(lignesCmd);

		List<MouvementDeStock> mouvementsDeStock = new ArrayList<MouvementDeStock>();
		List<Integer> quantites = mouvementDeStockService.getQuantiteByMagProdsIds(commandeReq.getIdMagasin(), ids);
		i = 0;
		commandeReq.getLignesCmdClient().forEach(ligne -> {
			mouvementsDeStock.add(new MouvementDeStock(ligne.getProduit(), magasin, TypeDeMvmt.VENTE,
					quantites.get(i++) - ligne.getQuantiteServie(), new Date()));
		});
		mouvementDeStockService.saveAll(mouvementsDeStock);

		System.out.println("query result : CommandeClient inserted");
		System.out.println("execution time : " + (System.currentTimeMillis() - startTime) + "ms");
	}

	public List<MonthlyCount> getCount() {
		return commandeClientRepo.commandeClientCountPerMonth();
	}
}
