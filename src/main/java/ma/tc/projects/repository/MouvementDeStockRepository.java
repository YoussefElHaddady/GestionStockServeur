package ma.tc.projects.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ma.tc.projects.entity.MouvementDeStock;
import ma.tc.projects.entity.Produit;

@Repository
public interface MouvementDeStockRepository extends JpaRepository<MouvementDeStock, Long> {

//	@Query(value = "SELECT ms.quantite FROM mouvement_de_stock ms WHERE ms.id_magasin = :magasinId AND ms.id_produit IN :produitsIds ORDER BY id_produit, date_mvmt DESC", 
//			nativeQuery = true)
//	@Query(value = "SELECT quantite FROM mouvement_de_stock ms WHERE ms.id_magasin = :magasinId AND ms.id_produit IN :produitsIds AND id_mvmt_stk = (SELECT MAX(id_mvmt_stk) FROM mouvement_de_stock ms2 WHERE ms.id_produit = ms2.id_produit) ORDER BY ms.id_produit;", 
	@Query(value = "SELECT quantite FROM mouvement_de_stock ms WHERE id_mvmt_stk = (SELECT MAX(id_mvmt_stk) FROM mouvement_de_stock ms2 WHERE ms2.id_produit = ms.id_produit AND ms2.id_magasin = :magasinId AND ms2.id_produit IN :produitsIds) ORDER BY ms.id_produit", nativeQuery = true)
	public List<Integer> findQuantiteByMagProd(@Param("magasinId") long magasinId,
			@Param("produitsIds") List<Long> produitsIds);

	public boolean existsByProduit(Produit produit);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM mouvement_de_stock WHERE id_produit = :idProduit", nativeQuery = true)
	public void deleteByProdId(@Param("idProduit") long idProduit);

	@Query(value = "SELECT * FROM mouvement_de_stock ms \n"
			+ "WHERE id_mvmt_stk IN (SELECT MAX(id_mvmt_stk) as max_id FROM mouvement_de_stock ms2 WHERE ms2.id_produit = :idProduit GROUP BY id_magasin)\n"
			+ "ORDER BY ms.id_magasin", nativeQuery = true)
	public List<MouvementDeStock> findDetails(@Param("idProduit") long idProduit);
}
