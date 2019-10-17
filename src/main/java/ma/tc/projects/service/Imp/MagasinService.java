package ma.tc.projects.service.Imp;

import java.util.List;
import java.util.Optional;

import ma.tc.projects.entity.AppUser;
import ma.tc.projects.repository.AppRoleRepository;
import ma.tc.projects.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Magasin;
import ma.tc.projects.repository.MagasinRepository;
import ma.tc.projects.service.ICrudService;

@Service
@Primary
public class MagasinService implements ICrudService<Magasin, Long> {

	@Autowired
	private MagasinRepository magasinRepo;

	@Autowired
	private AppUserRepository userRepo;

	@Override
	public List<Magasin> getAll() {
		return magasinRepo.findAll();
	}

	@Override
	public void add(Magasin magasin) {
		magasinRepo.save(magasin);
	}

	@Override
	public void update(Magasin magasin) {
		System.out.println("============ updating magasin =========" + magasin.getIdMagasin());

		magasinRepo.update(magasin.getIdMagasin(), magasin.getNom(), magasin.getAdresse(), magasin.getNumero_patente(),
				magasin.getSuperficie());
	}

	@Override
	public void delete(Long id_magasin) {
		Magasin a = new Magasin();
		a.setIdMagasin(id_magasin);
		magasinRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<Magasin> iterable) {
		magasinRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Magasin> iterable) {
		magasinRepo.deleteAll(iterable);
	}

	public Optional<Magasin> getByUsername(String username) {
		AppUser user = userRepo.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not find."));

		Optional<Magasin> m = magasinRepo.findByUser(user);
		return m;
	}

	public Magasin getById(long id_magasin) {
		Magasin magasin = magasinRepo.findById(id_magasin)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Magasin not find."));

		return magasin;
	}

	public void deleteByUserId(Long id_user) {
		magasinRepo.deleteByUserId(id_user);
	}

}
