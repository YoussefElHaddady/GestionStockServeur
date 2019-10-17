package ma.tc.projects.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.Categorie;
import ma.tc.projects.entity.Fournisseur;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

	Optional<Categorie> findByName(String name);

	boolean existsByName(String name);

	@Query(value = "SELECT count(id_fournisseur) as nb_fournisseurs FROM fournisseurs", nativeQuery = true)
	public int fournisseursCount();
}
