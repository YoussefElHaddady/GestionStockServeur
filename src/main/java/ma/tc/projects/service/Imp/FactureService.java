package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Facture;
import ma.tc.projects.repository.FactureRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class FactureService implements ICrudService<Facture, Long> {
	
	@Autowired
	private FactureRepository factureRepo;

	@Override
	public List<Facture> getAll() {
		return factureRepo.findAll();
	}

	@Override
	public void add(Facture facture) {
		factureRepo.save(facture);
	}

	@Override
	public void update(Facture facture) {
		factureRepo.save(facture);
	}

	@Override
	public void delete(Long id_facture) {
		Facture a = new Facture();
		a.setIdFacture(id_facture);
		factureRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<Facture> iterable) {
		factureRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Facture> iterable) {
		factureRepo.deleteAll(iterable);
	}

}
