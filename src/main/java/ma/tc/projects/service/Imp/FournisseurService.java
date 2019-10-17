package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Fournisseur;
import ma.tc.projects.repository.FournisseurRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class FournisseurService implements ICrudService<Fournisseur, Long> {
	
	@Autowired
	private FournisseurRepository fournisseurRepo;

	@Override
	public List<Fournisseur> getAll() {
		return fournisseurRepo.findAll();
	}

	@Override
	public void add(Fournisseur fournisseur) {
		fournisseurRepo.save(fournisseur);
	}

	@Override
	public void update(Fournisseur fournisseur) {
		fournisseurRepo.save(fournisseur);
	}

	@Override
	public void delete(Long id_fournisseur) {
		Fournisseur a = new Fournisseur();
		a.setIdFournisseur(id_fournisseur);
		fournisseurRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<Fournisseur> iterable) {
		fournisseurRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Fournisseur> iterable) {
		fournisseurRepo.deleteAll(iterable);
	}

	public int getCount() {
		return fournisseurRepo.fournisseursCount();
	}
}
