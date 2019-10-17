package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.LigneCmdFournisseur;
import ma.tc.projects.repository.LigneCmdFournisseurRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class LigneCmdFournisseurService implements ICrudService<LigneCmdFournisseur, Long> {
	
	@Autowired
	private LigneCmdFournisseurRepository ligneCmdFournisseurRepo;

	@Override
	public List<LigneCmdFournisseur> getAll() {
		return ligneCmdFournisseurRepo.findAll();
	}

	@Override
	public void add(LigneCmdFournisseur ligneCmdFournisseur) {
		ligneCmdFournisseurRepo.save(ligneCmdFournisseur);
	}

	@Override
	public void update(LigneCmdFournisseur ligneCmdFournisseur) {
		ligneCmdFournisseurRepo.save(ligneCmdFournisseur);
	}

	@Override
	public void delete(Long id_ligneCmdFournisseur) {
		LigneCmdFournisseur a = new LigneCmdFournisseur();
		a.setIdLigneCmdFournisseur(id_ligneCmdFournisseur);
		ligneCmdFournisseurRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<LigneCmdFournisseur> iterable) {
		ligneCmdFournisseurRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<LigneCmdFournisseur> iterable) {
		ligneCmdFournisseurRepo.deleteAll(iterable);
	}

}
