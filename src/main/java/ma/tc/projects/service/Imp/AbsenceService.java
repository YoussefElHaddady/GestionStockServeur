package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Absence;
import ma.tc.projects.entity.Ouvrier;
import ma.tc.projects.repository.AbsenceRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class AbsenceService implements ICrudService<Absence, Long> {
	
	@Autowired
	private AbsenceRepository absenceRepo;

	@Override
	public List<Absence> getAll() {
		return absenceRepo.findAll();
	}

	@Override
	public void add(Absence absence) {
		absenceRepo.save(absence);
	}

	@Override
	public void update(Absence absence) {
		absenceRepo.save(absence);
	}

	@Override
	public void delete(Long id_absence) {
		Absence a = new Absence();
		a.setIdAbsence(id_absence);
		absenceRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<Absence> iterable) {
		absenceRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Absence> iterable) {
		absenceRepo.deleteAll(iterable);
	}
	
	public List<Absence> getCurrentMonthAbsence(long idOuvrier) {
		return absenceRepo.getCurrentMonthAbsence(idOuvrier);
	}
	
	public List<Integer> getCurrentMonthAbsence() {
		return absenceRepo.getCurrentMonthAbsence();
	}
	
	public List<Absence> getMonthAbsence(long idOuvrier, int month) {
		if(month < 1 ) throw new RuntimeException("Fail! -> Cause: month number have to be Absolutely positive.");
		if(month > 12 ) throw new RuntimeException("Fail! -> Cause: month number must be less than 12");
		
		return absenceRepo.getMonthAbsence(idOuvrier, month);
	}
	
	public void deleteAbsences(long idOuvrier) {
		absenceRepo.deleteAbsences(idOuvrier);
	}
	
}
