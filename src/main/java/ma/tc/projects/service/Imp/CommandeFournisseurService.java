package ma.tc.projects.service.Imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.CommandeFournisseur;
import ma.tc.projects.entity.LigneCmdFournisseur;
import ma.tc.projects.entity.Magasin;
import ma.tc.projects.entity.MouvementDeStock;
import ma.tc.projects.entity.ReglementFournisseur;
import ma.tc.projects.enums.TypeDeMvmt;
import ma.tc.projects.message.request.CommandeFournisseurAddingRequest;
import ma.tc.projects.repository.CommandeFournisseurRepository;
import ma.tc.projects.service.ICrudService;

@Service
@Primary
public class CommandeFournisseurService implements ICrudService<CommandeFournisseur, Long> {

	@Autowired
	private CommandeFournisseurRepository commandeFournisseurRepo;

	@Autowired
	private MagasinService magasinService;

	@Autowired
	private FournisseurService fournisseurService;

	@Autowired
	private ReglementFournisseurService reglementService;

	@Autowired
	private LigneCmdFournisseurService ligneCmdFournisseurService;

	@Autowired
	private ProduitService produitService;

	@Autowired
	private CategorieService categorieService;

	@Autowired
	private MouvementDeStockService mouvementDeStockService;

	private int i;

	@Override
	public List<CommandeFournisseur> getAll() {
		return commandeFournisseurRepo.findAll();
	}

	@Override
	public void add(CommandeFournisseur commandeFournisseur) {
//		commandeFournisseurRepo.save(commandeFournisseur);
	}

	@Override
	public void update(CommandeFournisseur commandeFournisseur) {
		commandeFournisseurRepo.save(commandeFournisseur);
	}

	@Override
	public void delete(Long id_commandeFournisseur) {
		CommandeFournisseur a = new CommandeFournisseur();
		a.setIdCmdFournisseur(id_commandeFournisseur);
		commandeFournisseurRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<CommandeFournisseur> iterable) {
//		commandeFournisseurRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<CommandeFournisseur> iterable) {
		commandeFournisseurRepo.deleteAll(iterable);
	}

	public void addCommande(CommandeFournisseurAddingRequest commandeReq) {
		long startTime = System.currentTimeMillis();
		long generatedLong = ThreadLocalRandom.current().nextLong();
		Magasin magasin = magasinService.getById(commandeReq.getIdMagasin());

		// Insert Fournisseur if not exist in database
		if (commandeReq.getFournisseur().getIdFournisseur() <= 0) {
			fournisseurService.add(commandeReq.getFournisseur());
		}

		CommandeFournisseur cmd = new CommandeFournisseur("cmd" + generatedLong, commandeReq.getDateCmdF(),
				commandeReq.getMontantTotal(), commandeReq.getFournisseur());

		// Insert CommandeFournisseur object
		commandeFournisseurRepo.save(cmd);

		// Insert commandeFournisseur's ReglementFournisseur list objects
		commandeReq.getReglements().forEach(reglement -> reglementService
				.add(new ReglementFournisseur(new Date(), reglement.getMode(), reglement.getMontant(), cmd)));

		// Insert commandeFournisseur's LigneCmdFournisseur list objects
		List<LigneCmdFournisseur> lignesCmd = new ArrayList<>();
		List<Long> ids = new ArrayList<>();

		commandeReq.getLignesCmdFournisseur().forEach(ligne -> {
			ligne.setCommandeFournisseur(cmd);

			// Insert Produit if not exist in database
			if (ligne.getProduit().getIdProduit() <= 0) {

				// Insert Categorie if not exist in database
				if (ligne.getProduit().getCategorie().getIdCategorie() <= 0) {
					categorieService.add(ligne.getProduit().getCategorie());
				}

				produitService.add(ligne.getProduit());
				mouvementDeStockService
						.add(new MouvementDeStock(ligne.getProduit(), magasin, TypeDeMvmt.DEPOT, 0, new Date()));
			}
			ids.add(ligne.getProduit().getIdProduit());
			lignesCmd.add(ligne);
		});
		ligneCmdFournisseurService.saveAll(lignesCmd);

		// Insert MouvementDeStock list objects
		List<MouvementDeStock> mouvementsDeStock = new ArrayList<MouvementDeStock>();
		List<Integer> quantites = mouvementDeStockService.getQuantiteByMagProdsIds(commandeReq.getIdMagasin(), ids);
		i = 0;
		commandeReq.getLignesCmdFournisseur().forEach(ligne -> {
			mouvementsDeStock.add(new MouvementDeStock(ligne.getProduit(), magasin, TypeDeMvmt.DEPOT,
					quantites.get(i++) + ligne.getQuantite(), new Date()));
		});
		mouvementDeStockService.saveAll(mouvementsDeStock);

		System.out.println("query result : CommandeFournisseur inserted");
		System.out.println("execution time : " + (System.currentTimeMillis() - startTime) + "ms");
	}
}
