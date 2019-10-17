package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.ReglementClient;
import ma.tc.projects.repository.ReglementClientRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class ReglementClientService implements ICrudService<ReglementClient, Long> {
	
	@Autowired
	private ReglementClientRepository reglementRepo;

	@Override
	public List<ReglementClient> getAll() {
		return reglementRepo.findAll();
	}

	@Override
	public void add(ReglementClient reglement) {
		reglementRepo.save(reglement);
	}

	@Override
	public void update(ReglementClient reglement) {
		reglementRepo.save(reglement);
	}

	@Override
	public void delete(Long id_reglement) {
		ReglementClient a = new ReglementClient();
		a.setIdReglement(id_reglement);
		reglementRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<ReglementClient> iterable) {
		reglementRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<ReglementClient> iterable) {
		reglementRepo.deleteAll(iterable);
	}

}
