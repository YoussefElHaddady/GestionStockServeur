package ma.tc.projects.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.Categorie;
import ma.tc.projects.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Optional<Categorie> findByName(String name);

	boolean existsByName(String name);

	@Query(value = "SELECT count(id_client) as nb_clients FROM clients", nativeQuery = true)
	public int clientsCount();
}