package ma.tc.projects.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Client;
import ma.tc.projects.entity.CommandeClient;
import ma.tc.projects.repository.ClientRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class ClientService implements ICrudService<Client, Long> {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private CommandeClientService commandeClientService;

	@Override
	public List<Client> getAll() {
		return clientRepo.findAll();
	}

	@Override
	public void add(Client client) {
		clientRepo.save(client);
	}

	@Override
	public void update(Client client) {
		clientRepo.save(client);
	}

	@Override
	public void delete(Long id_client) {
		throw new RuntimeException("not implemented method Client.delete");
	}

	@Override
	public void saveAll(Iterable<Client> iterable) {
		clientRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Client> iterable) {
		iterable.forEach(item -> deleteControlled(item.getIdClient(), "any"));
	}

	public int getCount() {
		return clientRepo.clientsCount();
	}
	
	public double getCredit(Client client) {
		Client cli = clientRepo.findById(client.getIdClient()).orElseThrow(() -> new RuntimeException("Fail! -> Cause: Client not find ClientService.getCredit"));
		
		return cli.getCredit();
	}
	
	// le montant peut etre negati ou positif
	public boolean addCredit(long idClient, double montant) {
//		Client cli = clientRepo.findById(idClient).orElseThrow(() -> new RuntimeException("Fail! -> Cause: Client not find ClientService.getCredit"));
		Client cli = clientRepo.findById(idClient).orElse(null);
		
		if(cli == null)
			return false;
		
		cli.setCredit(cli.getCredit() + montant);
		clientRepo.save(cli);
		return true;
	}
	
	public boolean deleteControlled(long id_client, String decision) {
		Client client = clientRepo.findById(id_client).orElse(null);

		if (client == null)
			return false;
		
		if ("غير معروف".equals(client.getName()))
			return false;

		List<CommandeClient> cmdsClient = commandeClientService.getByClient(client);
		if (cmdsClient != null) {
			switch (decision.toLowerCase()) {
			case "delete":
				deleteAllCmds(cmdsClient);
				break;

			case "move":
				moveCmdsToUnknown(cmdsClient);
				break;

			default:
				return false;
			}
		}

		clientRepo.delete(client);
		return true;
	}
	
	public boolean moveCmdsToUnknown(List<CommandeClient> cmds) {
		Client unknown = clientRepo.findByName("غير معروف").orElse(null);

		if (cmds == null || unknown == null)
			return false;

		cmds.forEach(cmd -> {
			cmd.setClient(unknown);
			commandeClientService.update(cmd);
		});

		return true;
	}

	public boolean deleteAllCmds(List<CommandeClient> cmds) {
		if (cmds == null)
			return false;

		commandeClientService.deleteAll(cmds);
		return true;
	}
}
