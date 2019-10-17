package ma.tc.projects.service.Imp;

import java.util.List;

import ma.tc.projects.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Charge;
import ma.tc.projects.repository.ChargeRepository;
import ma.tc.projects.repository.TypeDeChargeRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class ChargeService implements ICrudService<Charge, Long> {
	
	@Autowired
	private ChargeRepository chargeRepo;

	@Autowired
	private AppUserRepository userRepo;

	@Autowired
	private TypeDeChargeRepository typeChargeRepo;

	@Override
	public List<Charge> getAll() {
		return chargeRepo.findAll();
	}

	@Override
	public void add(Charge charge) {
		
		charge.setTypeDeCharge(typeChargeRepo.findByLibele(charge.getTypeDeCharge().getLibele()).orElseThrow(() -> new RuntimeException("Fail! -> Cause: Charge type not find.")));
		charge.setUser(userRepo.findByUsername(charge.getUser().getUsername()).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not find.")));
		
		chargeRepo.save(charge);
	}

	@Override
	public void update(Charge charge) {
		
		charge.setUser(userRepo.findByUsername(charge.getUser().getUsername()).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not find.")));
		
		chargeRepo.save(charge);
	}

	@Override
	public void delete(Long id_charge) {
		Charge a = new Charge();
		a.setIdCharge(id_charge);
		chargeRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<Charge> iterable) {
		chargeRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Charge> iterable) {
		chargeRepo.deleteAll(iterable);
	}

}
