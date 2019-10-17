package ma.tc.projects.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ma.tc.projects.entity.Client;
import ma.tc.projects.repository.ClientRepository;
import ma.tc.projects.service.ICrudService;


@Service
@Primary
public class ClientService implements ICrudService<Client, Long> {
	
	@Autowired
	private ClientRepository clientRepo;

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
		Client a = new Client();
		a.setIdClient(id_client);
		clientRepo.delete(a);
	}

	@Override
	public void saveAll(Iterable<Client> iterable) {
		clientRepo.saveAll(iterable);
	}

	@Override
	public void deleteAll(Iterable<Client> iterable) {
		clientRepo.deleteAll(iterable);
	}

	public int getCount() {
		return clientRepo.clientsCount();
	}
}
