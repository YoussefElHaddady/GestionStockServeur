package ma.tc.projects.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

	public Optional<Categorie> findByLabel(String label);
	public boolean existsByLabel(String label);
	
	@Query(value = "SELECT id_categorie FROM categories ORDER BY id_categorie ASC LIMIT 1", nativeQuery = true)
	public long findFirstCategori();
	
	@Query(value = "SELECT c.id_categorie, c.label, c.description FROM categories c, produits p, mouvement_de_stock m WHERE c.id_categorie = p.id_categorie AND p.id_produit = m.id_produit AND m.id_magasin = :idMagasin GROUP BY c.id_categorie ORDER BY c.id_categorie", 
			nativeQuery = true)
	public List<Categorie> findByMagasin(@Param("idMagasin") long idMagasin);
}
