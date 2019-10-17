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
		Categorie a = new Categorie();
		a.setIdCategorie(id_categorie);
		categorieRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<Categorie> iterable) {
		categorieRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Categorie> iterable) {
		categorieRepo.deleteAll(iterable);
	}

	public List<Categorie> getAllByMagasin(long idMagasin) {
		List<Categorie> categories = categorieRepo.findByMagasin(idMagasin);

		categories.forEach(categorie -> {
			List<Produit> prods = produitService.getAllByMagasinCategorie(idMagasin, categorie.getIdCategorie());
			categorie.setProduits(prods);

			List<Long> ids = new ArrayList<>();
			prods.forEach(prod -> ids.add(prod.getIdProduit()));
			categorie.setQuantites(mouvementDeStockService.getQuantiteByMagProdsIds(idMagasin, ids));
		});

		return categories;
	}

}
