package ma.tc.projects.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ma.tc.projects.entity.Absence;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long>{
	
	@Query(value = "SELECT count(id_absence) FROM `absences` WHERE month(date_absence) = month(now()) AND year(date_absence) = year(now()) GROUP BY id_ouvrier ORDER BY id_ouvrier", nativeQuery = true)
	public List<Integer> getCurrentMonthAbsence();

	@Query(value = "SELECT * FROM absences WHERE id_ouvrier = :idOuvrier AND month(date_absence) = month(now()) AND year(date_absence) = year(now())", nativeQuery = true)
	public List<Absence> getCurrentMonthAbsence(@Param("idOuvrier") long idOuvrier);
	
	@Query(value = "SELECT * FROM absences WHERE id_ouvrier = :idOuvrier AND month(date_absence) = :month AND year(date_absence) = year(now())", nativeQuery = true)
	public List<Absence> getMonthAbsence(@Param("idOuvrier") long idOuvrier, @Param("month") int month);

	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM absences WHERE id_ouvrier = :idOuvrier", nativeQuery = true)
	public void deleteAbsences(@Param("idOuvrier") long idOuvrier);
}
	