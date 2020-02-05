package ma.tc.projects.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.CommandeFournisseur;
import ma.tc.projects.entity.Fournisseur;
import ma.tc.projects.message.response.MonthlyCount;

@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Long> {

	public Optional<CommandeFournisseur> findByCodeCmdF(String codeCmd);

	public Optional<List<CommandeFournisseur>> findByFournisseur(Fournisseur fournisseur);

	@Query(value = "SELECT YEAR(date_cmd) as year, MONTH(date_cmd) as month, count(id_commande_fournisseur) as count "
			+ "FROM commande_fournisseur " + "GROUP BY year, month", nativeQuery = true)
	public List<MonthlyCount> commandeFournisseurCountPerMonth();

	@Query(value = "SELECT id_fournisseur, count(id_commande_fournisseur) as count FROM commande_fournisseur GROUP BY id_fournisseur", nativeQuery = true)
	public List<MonthlyCount> commandeFournisseurCountPerFournisseur();

	@Query(value = "SELECT SUM(montant_total) FROM commande_fournisseur WHERE YEAR(date_cmdF) = YEAR(NOW()) AND MONTH(date_cmdF) = MONTH(NOW())", nativeQuery = true)
	public Integer getOutcomes();
}
