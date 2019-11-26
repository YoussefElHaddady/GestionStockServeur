package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.CommandeFournisseur;
import ma.tc.projects.entity.Fournisseur;
import ma.tc.projects.repository.FournisseurRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class FournisseurService implements ICrudService<Fournisseur, Long> {
	
	@Autowired
	private FournisseurRepository fournisseurRepo;
	
	@Autowired
	private CommandeFournisseurService commandeFournisseurService;

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
//		Fournisseur a = new Fournisseur();
//		a.setIdFournisseur(id_fournisseur);
//		fournisseurRepo.delete(a);
		throw new RuntimeException("not implemented method Fournisseur.delete");
	}

	@Override
	public void saveAll(Iterable<Fournisseur> iterable) {
		fournisseurRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Fournisseur> iterable) {
//		fournisseurRepo.deleteAll(iterable);
		iterable.forEach(item -> deleteControlled(item.getIdFournisseur(), "any"));
	}

	public int getCount() {
		return fournisseurRepo.fournisseursCount();
	}
	
	public boolean deleteControlled(long id_fournisseur, String decision) {
		Fournisseur fournisseur = fournisseurRepo.findById(id_fournisseur).orElse(null);

		if (fournisseur == null)
			return false;
		
		if ("غير معروف".equals(fournisseur.getName()))
			return false;

		List<CommandeFournisseur> cmdsFournisseur = commandeFournisseurService.getByFournisseur(fournisseur);
		if (cmdsFournisseur != null) {
			switch (decision.toLowerCase()) {
			case "delete":
				deleteAllCmds(cmdsFournisseur);
				break;

			case "move":
				moveCmdsToUnknown(cmdsFournisseur);
				break;

			default:
				return false;
			}
		}

		fournisseurRepo.delete(fournisseur);
		return true;
	}
	
	public boolean moveCmdsToUnknown(List<CommandeFournisseur> cmds) {
		Fournisseur unknown = fournisseurRepo.findByName("غير معروف").orElse(null);

		if (cmds == null || unknown == null)
			return false;

		cmds.forEach(cmd -> {
			cmd.setFournisseur(unknown);
			commandeFournisseurService.update(cmd);
		});

		return true;
	}

	public boolean deleteAllCmds(List<CommandeFournisseur> cmds) {
		if (cmds == null)
			return false;

		commandeFournisseurService.deleteAll(cmds);
		return true;
	}
}
