package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.ReglementFournisseur;
import ma.tc.projects.repository.ReglementFournisseurRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class ReglementFournisseurService implements ICrudService<ReglementFournisseur, Long> {
	
	@Autowired
	private ReglementFournisseurRepository reglementRepo;

	@Override
	public List<ReglementFournisseur> getAll() {
		return reglementRepo.findAll();
	}

	@Override
	public void add(ReglementFournisseur reglement) {
		reglementRepo.save(reglement);
	}

	@Override
	public void update(ReglementFournisseur reglement) {
		reglementRepo.save(reglement);
	}

	@Override
	public void delete(Long id_reglement) {
		ReglementFournisseur a = new ReglementFournisseur();
		a.setIdReglement(id_reglement);
		reglementRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<ReglementFournisseur> iterable) {
		reglementRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<ReglementFournisseur> iterable) {
		reglementRepo.deleteAll(iterable);
	}

}
