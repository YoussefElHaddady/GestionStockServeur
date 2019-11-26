package ma.tc.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ma.tc.projects.entity.CommandeClient;
import ma.tc.projects.entity.LigneCmdClient;
import ma.tc.projects.entity.Produit;

@Repository
public interface LigneCmdClientRepository extends JpaRepository<LigneCmdClient, Long> {

	public boolean existsByCommandeClient(CommandeClient commandeClient);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM ligne_cmd_client WHERE id_cmd_client = :idCmdCli", nativeQuery = true)
	public void deleteByCmdClientId(@Param("idCmdCli") long idCmdClient);
	
	public boolean existsByProduit(Produit produit);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM ligne_cmd_client WHERE id_produit = :idProduit", nativeQuery = true)
	public void deleteByProduitId(@Param("idProduit") long idProduit);
}
