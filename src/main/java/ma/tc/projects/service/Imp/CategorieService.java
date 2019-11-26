package ma.tc.projects.service.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Categorie;
import ma.tc.projects.entity.Produit;
import ma.tc.projects.repository.CategorieRepository;
import ma.tc.projects.service.ICrudService;

@Service
@Primary
public class CategorieService implements ICrudService<Categorie, Long> {

	@Autowired
	private CategorieRepository categorieRepo;

	@Autowired
	private ProduitService produitService;

	@Autowired
	private MouvementDeStockService mouvementDeStockService;

	@Override
	public List<Categorie> getAll() {
		return categorieRepo.findAll();
	}

	@Override
	public void add(Categorie categorie) {
		categorieRepo.save(categorie);
	}

	@Override
	public void update(Categorie categorie) {
		categorieRepo.save(categorie);
	}

	@Override
	public void delete(Long id_categorie) {
		throw new RuntimeException("not implemented method Categorie.delete");
	}

	@Override
	public void saveAll(Iterable<Categorie> iterable) {
		categorieRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Categorie> iterable) {
//		categorieRepo.deleteAll(iterable);
		iterable.forEach(item -> this.deleteControlled(item.getIdCategorie(), "any"));
	}
	
	public boolean exists(String label) {
		return categorieRepo.existsByLabel(label);
	}
	
	public Categorie findByLabel(String label) {
		return categorieRepo.findByLabel(label).orElse(null);
	}

	public List<Categorie> getAllByMagasin(long idMagasin) {
		Categorie notCat = categorieRepo.findByLabel("غير مصنف").get();

		List<Categorie> categories = categorieRepo.findByMagasin(idMagasin, notCat.getIdCategorie());

		categories.forEach(categorie -> {
			List<Produit> prods = produitService.getAllByMagasinCategorie(idMagasin, categorie.getIdCategorie());
			categorie.setProduits(prods);

			List<Long> ids = new ArrayList<>();
			prods.forEach(prod -> ids.add(prod.getIdProduit()));
			if (ids.size() != 0)
				categorie.setQuantites(mouvementDeStockService.getQuantiteByMagProdsIds(idMagasin, ids));
		});

		return categories;
	}

	public boolean deleteControlled(long id_categorie, String decision) {
		Categorie cat = categorieRepo.findById(id_categorie).orElse(null);

		if (cat == null)
			return false;
		if ("غير مصنف".equals(cat.getLabel()))
			return false;

		if (cat.getProduits() != null || cat.getProduits().size() >= 0) {
			switch (decision.toLowerCase()) {
			case "delete":
				produitService.deleteAll(cat.getProduits());
				break;

			case "move":
				moveProdsToNC(cat);
				break;

			default:
				return false;
			}
		}

		categorieRepo.delete(cat);
		return true;
	}

	public boolean moveProdsToNC(Categorie categorie) {
		Categorie notCat = categorieRepo.findByLabel("غير مصنف").orElse(null);

		if (categorie == null || notCat == null)
			return false;

		if (categorie.getProduits() == null)
			return false;

		categorie.getProduits().forEach(produit -> {
			produit.setCategorie(notCat);
			produitService.update(produit);
		});

		return true;
	}

	public boolean deleteAllProducts(Categorie categorie) {
		if (categorie == null || categorie.getProduits() == null)
			return false;

		produitService.deleteAll(categorie.getProduits());
		return true;
	}

}
