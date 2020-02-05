package ma.tc.projects.service.Imp;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Categorie;
import ma.tc.projects.entity.CommandeFournisseur;
import ma.tc.projects.entity.DetailProduit;
import ma.tc.projects.entity.Fournisseur;
import ma.tc.projects.entity.Magasin;
import ma.tc.projects.entity.MouvementDeStock;
import ma.tc.projects.entity.Produit;
import ma.tc.projects.entity.ReglementFournisseur;
import ma.tc.projects.enums.Code;
import ma.tc.projects.enums.ModeReglementEnum;
import ma.tc.projects.enums.TypeDeMvmt;
import ma.tc.projects.message.request.CommandeFournisseurAddingRequest;
import ma.tc.projects.message.response.MonthlyCount;
import ma.tc.projects.repository.CommandeFournisseurRepository;
import ma.tc.projects.service.ICrudService;

@Service
@Primary
public class CommandeFournisseurService implements ICrudService<CommandeFournisseur, Long> {

	@Autowired
	private CommandeFournisseurRepository commandeFournisseurRepo;

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

	@Override
	public List<CommandeFournisseur> getAll() {
		return commandeFournisseurRepo.findAll();
	}

	@Override
	public void add(CommandeFournisseur commandeFournisseur) {
		throw new RuntimeException("not implemented method CommandeFournisseurService.add");
	}

	@Override
	public void update(CommandeFournisseur commandeFournisseur) {
		commandeFournisseurRepo.save(commandeFournisseur);
	}

	@Override
	public void delete(Long id_commandeFournisseur) {
		throw new RuntimeException("not implemented method CommandeFournisseurService.delete");
	}

	@Override
	public void saveAll(Iterable<CommandeFournisseur> iterable) {
		throw new RuntimeException("not implemented method CommandeFournisseurService.saveAll");
	}

	@Override
	public void deleteAll(Iterable<CommandeFournisseur> iterable) {
		iterable.forEach(item -> this.deleteControlled(item.getIdCmdFournisseur()));
	}

	public List<CommandeFournisseur> getByFournisseur(Fournisseur fournisseur) {
		return commandeFournisseurRepo.findByFournisseur(fournisseur).orElse(null);
	}

	public String addCommande(CommandeFournisseurAddingRequest commandeReq) {
		long startTime = System.currentTimeMillis();

		// Insert Fournisseur if not exist in database
		if (commandeReq.getFournisseur().getIdFournisseur() <= 0) {
			fournisseurService.add(commandeReq.getFournisseur());
		}

		String code = Code.generate("F" + commandeReq.getFournisseur().getIdFournisseur());
		CommandeFournisseur cmd = new CommandeFournisseur(code, commandeReq.getDateCmdF(),
				commandeReq.getMontantTotal(), commandeReq.getFournisseur());

		// Insert CommandeFournisseur object
		commandeFournisseurRepo.save(cmd);

		// Insert the list of ReglementFournisseur objects (for the current command)
		commandeReq.getReglements().forEach(reglement -> {
			if (reglement.getMode() == ModeReglementEnum.CREDIT)
				fournisseurService.addCredit(commandeReq.getFournisseur(), -1 * reglement.getMontant());

			reglementService
					.add(new ReglementFournisseur(new Date(), reglement.getMode(), reglement.getMontant(), cmd));
		});

		// Insert the list of LigneCmdFournisseur objects (for the current command)
		commandeReq.getLignesCmdFournisseur().forEach(ligne -> {
			ligne.setCommandeFournisseur(cmd);
			Produit prodLigne = ligne.getProduit();
			Categorie catProd = prodLigne.getCategorie();

			// Insert Produit if not exist in database
			if (prodLigne.getIdProduit() <= 0) {

				// Insert Categorie if not exist in database
				if (catProd.getIdCategorie() <= 0) {
					if (categorieService.existsByLabel(catProd.getLabel()))
						prodLigne.setCategorie(categorieService.findByLabel(catProd.getLabel()));
					else
						categorieService.add(catProd);
				}
				if (produitService.existsByLibelle(prodLigne.getLibelle()))
					prodLigne.setIdProduit(produitService.getByLibelle(prodLigne.getLibelle()).getIdProduit());
				else
					produitService.add(prodLigne);
			}

			List<MouvementDeStock> originDetails = mouvementDeStockService.getDetailsProduit(prodLigne.getIdProduit());
			DetailProduit detail = prodLigne.getDetails().get(0);

			// prodLigne.getDetails().forEach(detail -> {
			MouvementDeStock mm = originDetails.stream()
					.filter(dt -> dt.getMagasin().getIdMagasin() == detail.getIdMagasin()).findFirst().orElse(null);

			int quantite = mm != null ? mm.getQuantite() : 0;

			mouvementDeStockService.add(new MouvementDeStock(new Date(), detail.getPrixAchat(), detail.getPrixVente(),
					quantite + detail.getQuantite(), TypeDeMvmt.DEPOT, prodLigne, new Magasin(detail.getIdMagasin())));
			// });

			ligneCmdFournisseurService.add(ligne);
		});

		System.out.println("query result : CommandeFournisseur inserted");
		System.out.println("execution time : " + (System.currentTimeMillis() - startTime) + "ms");
		return cmd.getCodeCmdF();
	}

	public List<MonthlyCount> getCount() {
		return commandeFournisseurRepo.commandeFournisseurCountPerMonth();
	}

	public int getOutcomes() {
		Integer outcomes = commandeFournisseurRepo.getOutcomes();
		return outcomes == null ? 0 : outcomes.intValue();
	}

	public boolean deleteControlled(long idCommandeFournisseur) {
		CommandeFournisseur commandeFournisseur = commandeFournisseurRepo.findById(idCommandeFournisseur).orElse(null);

		if (commandeFournisseur == null)
			return false;

		if (commandeFournisseur.getReglements() == null)
			reglementService.deleteAll(commandeFournisseur.getReglements());

		ligneCmdFournisseurService.deleteByCommandeFournisseur(commandeFournisseur);
		commandeFournisseurRepo.delete(commandeFournisseur);

		return true;
	}

}
