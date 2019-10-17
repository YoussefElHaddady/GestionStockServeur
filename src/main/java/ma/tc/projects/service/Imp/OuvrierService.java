package ma.tc.projects.service.Imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Absence;
import ma.tc.projects.entity.Ouvrier;
import ma.tc.projects.message.request.WorkerAbsence;
import ma.tc.projects.repository.OuvrierRepository;
import ma.tc.projects.service.ICrudService;

@Service
@Primary
public class OuvrierService implements ICrudService<Ouvrier, Long> {

	@Autowired
	private OuvrierRepository ouvrierRepo;

	@Autowired
	private AbsenceService absenceService;

	@Override
	public List<Ouvrier> getAll() {
		long startTime = System.currentTimeMillis();
		
		List<Ouvrier> ouvriers = ouvrierRepo.findOuvriers();
		
		ouvriers.forEach(ouvrier -> {
			List<Absence> absences = absenceService.getCurrentMonthAbsence(ouvrier.getIdOuvrier());
			ouvrier.setAbsences(absences);
		});
		
		System.out.println("query result : Ouvriers fetched");
		System.out.println("execution time : " + (System.currentTimeMillis() - startTime) + "ms");
		return ouvriers;
	}

	@Override
	public void add(Ouvrier ouvrier) {
		ouvrierRepo.save(ouvrier);
	}

	@Override
	public void update(Ouvrier ouvrier) {
//		ouvrierRepo.save(ouvrier);
		ouvrierRepo.updateButAbsences(ouvrier.getIdOuvrier(), ouvrier.getCIN(), ouvrier.getName(), ouvrier.getRIP(),
				ouvrier.getPhone(), ouvrier.getAddress(), ouvrier.getEmail(), ouvrier.getSalaire(),
				ouvrier.getAvance());
	}

	@Override
	public void delete(Long id_ouvrier) {
		Ouvrier ouvrier = ouvrierRepo.findById(id_ouvrier)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Ouvrier not find."));

		absenceService.deleteAbsences(ouvrier.getIdOuvrier());
		
		ouvrierRepo.delete(ouvrier);
	}

	@Override
	public void saveAll(Iterable<Ouvrier> iterable) {
		ouvrierRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Ouvrier> iterable) {
		ouvrierRepo.deleteAll(iterable);
	}

	public void signAbsence(Absence absence) {
		Ouvrier ouvrier = ouvrierRepo.findById(absence.getOuvrier().getIdOuvrier())
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Ouvrier not find."));

		ouvrier.addAbsence(absence);
		ouvrier.decreaseAvanceAtAbsenceSign();
		ouvrierRepo.save(ouvrier);
	}

	public void giveAmount(Ouvrier ouvrier, double montant) {
		Ouvrier ov = ouvrierRepo.findById(ouvrier.getIdOuvrier())
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Ouvrier not find."));

		System.out.println("oooooo : " + ov.getIdOuvrier());
		System.out.println("mmmmmm : " + montant);

		ov.decreaseAvanceBy(montant);
		ouvrierRepo.save(ov);
	}

	@Scheduled(cron = "0 0 0 1/1 * ?") // every day at 12h00 AM
//	@Scheduled(cron = "0 0 0 1 1/1 ?")	// monthly at 12:00 AM
	public void increaseAvanceAtMonthEnd() {

		ouvrierRepo.increaseAvanceAtMonthEnd();

		// print the current dateTime
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		System.out.println("========== increasing avance (at " + formatter.format(new Date()) + " ) ...");
	}

	// fetch Absence number of all Ouvriers for the current month
	public List<Integer> getAbsenceList() {
		return absenceService.getCurrentMonthAbsence();
	}

	// fetch absence list of the Ouvrier (given in params) for the current month
	public List<Absence> getOuvrierAbsenceList(Ouvrier ouvrier) {
		ouvrierRepo.findById(ouvrier.getIdOuvrier())
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Ouvrier not find."));

		return absenceService.getCurrentMonthAbsence(ouvrier.getIdOuvrier());
	}

	// fetch absence list of the Ouvrier (given in params) for the given month
	public List<Absence> getOuvrierAbsenceList(WorkerAbsence wa) {
		ouvrierRepo.findById(wa.getIdOuvrier())
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Ouvrier not find."));

		return absenceService.getMonthAbsence(wa.getIdOuvrier(), wa.getMonth());
	}
	
	public int getCount() {
		return ouvrierRepo.ouvriersCount();
	}

	/*
	 * // @Scheduled(cron = "0 0/15 * 1/1 * ?") // every 15min // @Scheduled(cron =
	 * "0 0 0/1 1/1 * ?") // every hour
	 * 
	 * @Scheduled(cron = "0 0 0 1/1 * ?") // every day at 12h00 AM
	 * // @Scheduled(cron = "0 0 0 1 1/1 ?") // monthly at 12:00 AM public void
	 * resetAbsenceNumberForAllWorkers() { ouvrierRepo.resetWorkersAbsenceNb();
	 * 
	 * // print the current dateTime SimpleDateFormat formatter = new
	 * SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	 * System.out.println("========== resetting workers absence number(at " +
	 * formatter.format(new Date()) + " ) ..."); }
	 */
}
