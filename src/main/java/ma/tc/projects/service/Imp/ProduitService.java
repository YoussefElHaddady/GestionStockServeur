package ma.tc.projects.service.Imp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
		Produit a = new Produit();
		a.setIdProduit(id_produit);
		produitRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<Produit> iterable) {
		produitRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Produit> iterable) {
		produitRepo.deleteAll(iterable);
	}

	public Produit getByLibelle(String libelle) {
		return produitRepo.findByLibelle(libelle);
	}

	public Produit getByCodeProduit(String code) {
		return produitRepo.findByCodeProduit(code);
	}

	public List<Produit> getAllByMagasinCategorie(long idMagasin, long idCategorie) {
		return produitRepo.findByMagasinCategorie(idMagasin, idCategorie);
	}

	public List<ProductCount> getCount() {
		List<List<Integer>> results = produitRepo.produitsCount();
		List<ProductCount> productCounts = new ArrayList<>();
		
		results.forEach(rslt -> {
			productCounts.add(new ProductCount(magasinService.getById(rslt.get(0)), rslt.get(1)));
		});
		
		return productCounts;
	}
}
