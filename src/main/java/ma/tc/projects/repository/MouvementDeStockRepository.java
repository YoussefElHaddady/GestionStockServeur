package ma.tc.projects.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.MouvementDeStock;

@Repository
public interface MouvementDeStockRepository extends JpaRepository<MouvementDeStock, Long> {

//	@Query(value = "SELECT ms.quantite FROM mouvement_de_stock ms WHERE ms.id_magasin = :magasinId AND ms.id_produit IN :produitsIds ORDER BY id_produit, date_mvmt DESC", 
//			nativeQuery = true)
//	@Query(value = "SELECT quantite FROM mouvement_de_stock ms WHERE ms.id_magasin = :magasinId AND ms.id_produit IN :produitsIds AND id_mvmt_stk = (SELECT MAX(id_mvmt_stk) FROM mouvement_de_stock ms2 WHERE ms.id_produit = ms2.id_produit) ORDER BY ms.id_produit;", 
	@Query(value = "SELECT quantite FROM mouvement_de_stock ms WHERE id_mvmt_stk = (SELECT MAX(id_mvmt_stk) FROM mouvement_de_stock ms2 WHERE ms2.id_produit = ms.id_produit AND ms2.id_magasin = :magasinId AND ms2.id_produit IN :produitsIds) ORDER BY ms.id_produit", nativeQuery = true)
	public List<Integer> findQuantiteByMagProd(@Param("magasinId") long magasinId,
			@Param("produitsIds") List<Long> produitsIds);
}
