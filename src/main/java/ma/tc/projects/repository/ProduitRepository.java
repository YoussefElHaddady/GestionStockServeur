package ma.tc.projects.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

	public Produit findByLibelle(String str);

	public Produit findByCodeProduit(String str);

	@Query(value = "SELECT p.code_produit, p.date_exp, p.date_pro, p.description, p.id_categorie, p.id_produit, p.image, p.libelle, p.prix_dachat, p.prix_unitaire FROM produits p, mouvement_de_stock m WHERE p.id_produit = m.id_produit AND m.id_magasin = :idMagasin AND id_categorie = :idCategorie GROUP BY p.id_produit ORDER BY p.id_produit", nativeQuery = true)
	public List<Produit> findByMagasinCategorie(@Param("idMagasin") long idMagasin,
			@Param("idCategorie") long idCategorie);

	@Query(value = "SELECT id_magasin, count(DISTINCT id_produit) as nb_prods FROM mouvement_de_stock GROUP BY id_magasin", nativeQuery = true)
	public List<List<Integer>> produitsCount();
}
