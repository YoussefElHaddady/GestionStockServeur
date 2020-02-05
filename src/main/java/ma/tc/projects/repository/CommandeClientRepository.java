package ma.tc.projects.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.Client;
import ma.tc.projects.entity.CommandeClient;
import ma.tc.projects.message.response.MonthlyCount;

@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long>{

	public Optional<CommandeClient> findByCodeCmd(String codeCmd);
	public Optional<List<CommandeClient>> findByClient(Client client);

	@Query(value = "SELECT YEAR(date_cmd) as year, MONTH(date_cmd) as month, count(id_commande_client) as count "
			+ "FROM commande_client "
			+ "GROUP BY year, month", nativeQuery = true)
	public List<MonthlyCount> commandeClientCountPerMonth();
	
	@Query(value = "SELECT id_client, count(id_commande_client) as count FROM commande_client GROUP BY id_client", nativeQuery = true)
	public List<MonthlyCount> commandeClientCountPerClient();
	
	@Query(value = "SELECT SUM(montant_paye) FROM commande_client WHERE YEAR(date_cmd) = YEAR(NOW()) AND MONTH(date_cmd) = MONTH(NOW())", nativeQuery = true)
	public Integer getIncomes();
}
