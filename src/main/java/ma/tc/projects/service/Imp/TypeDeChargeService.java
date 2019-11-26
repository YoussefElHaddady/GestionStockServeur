package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.TypeDeCharge;
import ma.tc.projects.repository.TypeDeChargeRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class TypeDeChargeService implements ICrudService<TypeDeCharge, Long> {
	
	@Autowired
	private TypeDeChargeRepository typeDeChargeRepo;
	
	@Autowired
	private ChargeService chargeService;

	@Override
	public List<TypeDeCharge> getAll() {
		return typeDeChargeRepo.findAll();
	}

	@Override
	public void add(TypeDeCharge typeDeCharge) {
		typeDeChargeRepo.save(typeDeCharge);
	}

	@Override
	public void update(TypeDeCharge typeDeCharge) {
		typeDeChargeRepo.save(typeDeCharge);
	}

	@Override
	public void delete(Long id_typeDeCharge) {
		
	}

	@Override
	public void saveAll(Iterable<TypeDeCharge> iterable) {
		typeDeChargeRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<TypeDeCharge> iterable) {
		typeDeChargeRepo.deleteAll(iterable);
	}
	
	public boolean deleteControlled(Long id_typeDeCharge) {
		TypeDeCharge type = typeDeChargeRepo.findById(id_typeDeCharge).orElse(null);
		
		if(type == null)
			return false;
		
		chargeService.deleteByTypeDeCharge(type);
		typeDeChargeRepo.delete(type);
		
		return true;
	}

}
