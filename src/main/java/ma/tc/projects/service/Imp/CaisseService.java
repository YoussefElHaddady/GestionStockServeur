package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Caisse;
import ma.tc.projects.repository.CaisseRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class CaisseService implements ICrudService<Caisse, Long> {
	
	@Autowired
	private CaisseRepository caisseRepo;

	@Override
	public List<Caisse> getAll() {
		return caisseRepo.findAll();
	}

	@Override
	public void add(Caisse caisse) {
		caisseRepo.save(caisse);
	}

	@Override
	public void update(Caisse caisse) {
		caisseRepo.save(caisse);
	}

	@Override
	public void delete(Long id_caisse) {
		Caisse a = new Caisse();
		a.setIdCaisse(id_caisse);
		caisseRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<Caisse> iterable) {
		caisseRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Caisse> iterable) {
		caisseRepo.deleteAll(iterable);
	}

}
