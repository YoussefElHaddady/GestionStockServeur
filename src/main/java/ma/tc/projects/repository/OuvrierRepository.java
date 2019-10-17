package ma.tc.projects.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ma.tc.projects.entity.Ouvrier;

@Repository
public interface OuvrierRepository extends JpaRepository<Ouvrier, Long> {

//	@Transactional
//	@Modifying
//	@Query(value = "UPDATE ouvriers SET nb_dabsence = 0", nativeQuery = true)
//	public void resetWorkersAbsenceNb();

	@Query(value = "SELECT id_ouvrier, cin, rip, address, email, name, phone, picture, salaire, avance FROM ouvriers", nativeQuery = true)
	public List<Ouvrier> findOuvriers();

	@Transactional
	@Modifying
	@Query(value = "UPDATE ouvriers SET cin = :cin, name = :name, rip = :rip,"
			+ " phone = :phone, address = :address, email = :email, salaire = :salaire,"
			+ " avance = :avance WHERE id_ouvrier = :idOuvrier", nativeQuery = true)
	public void updateButAbsences(@Param("idOuvrier") long idOuvrier, @Param("cin") String cin,
			@Param("name") String name, @Param("rip") double rip, @Param("phone") String phone,
			@Param("address") String address, @Param("email") String email, @Param("salaire") double salaire,
			@Param("avance") double avance);

	@Transactional
	@Modifying
	@Query(value = "UPDATE ouvriers SET avance = avance + salaire", nativeQuery = true)
	public void increaseAvanceAtMonthEnd();

	@Query(value = "SELECT count(id_ouvrier) as nb_ouvriers FROM ouvriers", nativeQuery = true)
	public int ouvriersCount();
}
