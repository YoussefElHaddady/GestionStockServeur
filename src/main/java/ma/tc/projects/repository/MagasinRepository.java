package ma.tc.projects.repository;

import java.util.Optional;

import ma.tc.projects.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ma.tc.projects.entity.Magasin;

@Repository
public interface MagasinRepository extends JpaRepository<Magasin, Long>{

	Optional<Magasin> findByUser(AppUser user);
	boolean existsByUser(AppUser user);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE magasins SET nom = :newNom, adresse = :newAdresse, numero_patente = :newNumeroPatente, superficie = :newSuperficie WHERE id_magasin = :idMagasin", nativeQuery = true)
	public void update(@Param("idMagasin") long idMagasin, @Param("newNom") String nom, @Param("newAdresse") String adresse, @Param("newNumeroPatente") int numero_patent, @Param("newSuperficie") int superficie);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM magasins WHERE id_user = :idUser", nativeQuery = true)
	public void deleteByUserId(@Param("idUser") long idUser);
}
