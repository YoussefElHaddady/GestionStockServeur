package ma.tc.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ma.tc.projects.entity.CommandeFournisseur;
import ma.tc.projects.entity.LigneCmdFournisseur;
import ma.tc.projects.entity.Produit;

@Repository
public interface LigneCmdFournisseurRepository extends JpaRepository<LigneCmdFournisseur, Long>{

	public boolean existsByCommandeFournisseur(CommandeFournisseur commandeFournisseur);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM ligne_cmd_fournisseur WHERE id_cmd_fournisseur = :idCmdFournisseur", nativeQuery = true)
	public void deleteByCmdFournisseurId(@Param("idCmdFournisseur") long idCmdFournisseur);
	
	public boolean existsByProduit(Produit produit);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM ligne_cmd_fournisseur WHERE id_produit = :idProduit", nativeQuery = true)
	public void deleteByProdId(@Param("idProduit") long idProduit);
}
