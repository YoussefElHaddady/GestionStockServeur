package ma.tc.projects.service.Imp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Categorie;
import ma.tc.projects.entity.DetailProduit;
import ma.tc.projects.entity.MouvementDeStock;
import ma.tc.projects.entity.Produit;
import ma.tc.projects.message.response.ProductCount;
import ma.tc.projects.repository.ProduitRepository;
import ma.tc.projects.service.ICrudService;

@Service
@Primary
public class ProduitService implements ICrudService<Produit, Long> {

	@Autowired
	private ProduitRepository produitRepo;

	@Autowired
	private LigneCmdClientService ligneCmdClientService;

	@Autowired
	private LigneCmdFournisseurService ligneCmdFournisseurService;

	@Autowired
	private MouvementDeStockService mouvementDeStockService;

	@Autowired
	private MagasinService magasinService;

	@Override
	public List<Produit> getAll() {
		return produitRepo.findAll();
	}

	@Override
	public void add(Produit produit) {
		long generatedLong = ThreadLocalRandom.current().nextLong();

		if (produit.getCodeProduit() == null)
			produit.setCodeProduit("prod" + generatedLong);

		produitRepo.save(produit);
	}

	@Override
	public void update(Produit produit) {
		produitRepo.save(produit);
	}

	@Override
	public void delete(Long id_produit) {
		throw new RuntimeException("not implemented method Produit.delete");
	}

	@Override
	public void saveAll(Iterable<Produit> iterable) {
		produitRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Produit> iterable) {
		// produitRepo.deleteAll(iterable);

		iterable.forEach(item -> this.deleteControlled(item.getIdProduit(), "any"));
	}

	public Produit getByLibelle(String libelle) {
		return produitRepo.findByLibelle(libelle);
	}

	public Produit getByCodeProduit(String code) {
		return produitRepo.findByCodeProduit(code);
	}

	public List<Produit> getByCategorieId(long id_categorie) {

		List<Produit> produits = produitRepo.findByCategorie(new Categorie(id_categorie, null, null));

		produits.forEach(produit -> {
			List<MouvementDeStock> mvmts = mouvementDeStockService.getDetailsProduit(produit.getIdProduit());
			List<DetailProduit> details = new ArrayList<>();

			mvmts.forEach(mvmt -> {
				details.add(new DetailProduit(mvmt.getMagasin().getIdMagasin(), mvmt.getDateMvmt(), mvmt.getPrixAchat(),
						mvmt.getPrixVente(), mvmt.getQuantite()));
			});
			produit.setDetails(details);
		});

		return produits;
	}

//	public List<Produit> getAllByMagasinCategorie(long idMagasin, long idCategorie) {
//		return produitRepo.findByMagasinCategorie(idMagasin, idCategorie);
//	}

	public List<Produit> getAllByMagasinCategorie(long idMagasin, long idCategorie) {
		List<Produit> produits = produitRepo.findAll();

		return produits;
	}

	public List<ProductCount> getCount() {
		List<List<Integer>> results = produitRepo.produitsCount();
		List<ProductCount> productCounts = new ArrayList<>();

		results.forEach(rslt -> {
			productCounts.add(new ProductCount(magasinService.getById(rslt.get(0)), rslt.get(1)));
		});

		return productCounts;
	}

	public boolean deleteControlled(long idProduit, String decision) {
//		if (produitRepo.findById(idProduit).isPresent() == false)
//			return false;

		Produit prod = produitRepo.findById(idProduit).orElse(null);

		if (prod == null)
			return false;

		ligneCmdClientService.deleteByProduit(prod);
		ligneCmdFournisseurService.deleteByProduit(prod);
		mouvementDeStockService.deleteByProduit(prod);
		produitRepo.delete(prod);

		return true;
	}
}
