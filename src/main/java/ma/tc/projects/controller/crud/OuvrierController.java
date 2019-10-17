package ma.tc.projects.controller.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.controller.CrudController;
import ma.tc.projects.entity.Absence;
import ma.tc.projects.entity.Ouvrier;
import ma.tc.projects.message.request.WorkerAbsence;
import ma.tc.projects.service.Imp.OuvrierService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/ouvrier")
public class OuvrierController extends CrudController<Ouvrier, Long> {

	@Autowired
	private OuvrierService ouvrierService;

	@PostMapping("/ouvrier_absence/sign")
	public void marquerAbsence(@RequestBody Absence absence) {
		ouvrierService.signAbsence(absence);
	}

	@PostMapping("/ouvrier_absence/give_amount")
	public void donnerMontant(@RequestBody Ouvrier ouvrier) {
		// to not define a new class we'll use avance as the given amount (the correct
		// avance will be fetched in the service)
		ouvrierService.giveAmount(ouvrier, ouvrier.getAvance());
	}
	
	@GetMapping("/ouvrier_absence/list/numbers")
	public List<Integer> getAbsenceList() {
		return ouvrierService.getAbsenceList();
	}
	
	@PostMapping("/ouvrier_absence/list/curr_month")
	public List<Absence> getOuvrierAbsenceList(@RequestBody Ouvrier ouvrier) {
		return ouvrierService.getOuvrierAbsenceList(ouvrier);
	}
	
	@PostMapping("/ouvrier_absence/list/per_month")
	public List<Absence> getOuvrierAbsenceList(@RequestBody WorkerAbsence wa) {
		return ouvrierService.getOuvrierAbsenceList(wa);
	}
}
