package ma.tc.projects.service.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Categorie;
import ma.tc.projects.entity.MouvementDeStock;
import ma.tc.projects.repository.CategorieRepository;
import ma.tc.projects.repository.MouvementDeStockRepository;
import ma.tc.projects.service.ICrudService;

@Service
@Primary
public class MouvementDeStockService implements ICrudService<MouvementDeStock, Long> {

	@Autowired
	private MouvementDeStockRepository mouvementDeStockRepo;

	@Autowired
	public CategorieRepository categorieRepo;

	@Override
	public List<MouvementDeStock> getAll() {
		return mouvementDeStockRepo.findAll();
	}

	@Override
	public void add(MouvementDeStock mouvementDeStock) {
		mouvementDeStockRepo.save(mouvementDeStock);
	}

	@Override
	public void update(MouvementDeStock mouvementDeStock) {
		mouvementDeStockRepo.save(mouvementDeStock);
	}

	@Override
	public void delete(Long id_mouvementDeStock) {
		MouvementDeStock a = new MouvementDeStock();
		a.setIdMvmtStk(id_mouvementDeStock);
		mouvementDeStockRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<MouvementDeStock> iterable) {
		mouvementDeStockRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<MouvementDeStock> iterable) {
		mouvementDeStockRepo.deleteAll(iterable);
	}

	public List<Integer> getQuantiteByMagProd(long id_magasin, Long id_categorie) {

		Categorie cat = categorieRepo.findById(id_categorie)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Categorie not find."));

		List<Long> ids = new ArrayList<>();
		cat.getProduits().forEach(prod -> ids.add(prod.getIdProduit()));

		return mouvementDeStockRepo.findQuantiteByMagProd(id_magasin, ids);
	}

	public List<Integer> getQuantiteByMagProdsIds(long id_magasin, List<Long> prodsIds) {

		return mouvementDeStockRepo.findQuantiteByMagProd(id_magasin, prodsIds);
	}

	public List<Integer> getQuantitiesForTheFirstCategory(long id_magasin) {
		long id_cat = categorieRepo.findFirstCategori();

		return getQuantiteByMagProd(id_magasin, id_cat);
	}

}
