package ma.tc.projects.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.CommandeClient;
import ma.tc.projects.message.response.MonthlyCount;

@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long>{

	public Optional<CommandeClient> findByCodeCmd(String codeCmd);

	@Query(value = "SELECT YEAR(date_cmd) as year, MONTH(date_cmd) as month, count(id_commande_client) as count "
			+ "FROM commande_client "
			+ "GROUP BY year, month", nativeQuery = true)
	public List<MonthlyCount> commandeClientCountPerMonth();
	
	@Query(value = "SELECT id_client, count(id_commande_client) as count FROM commande_client GROUP BY id_client", nativeQuery = true)
	public List<MonthlyCount> commandeClientCountPerClient();
}
