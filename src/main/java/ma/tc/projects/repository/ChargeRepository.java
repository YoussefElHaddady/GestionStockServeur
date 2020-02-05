package ma.tc.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ma.tc.projects.entity.Charge;
import ma.tc.projects.entity.TypeDeCharge;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long> {
	
	public boolean existsByTypeDeCharge(TypeDeCharge typeDeCharge);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM charges WHERE id_type_de_charge = :idTypeCharge", nativeQuery = true)
	public void deleteByTypeDeCharge(@Param("idTypeCharge") long idTypeCharge);
	
	@Query(value = "SELECT SUM(montant) FROM charges WHERE etat = 'PAYE' AND YEAR(date_charge) = YEAR(NOW()) AND MONTH(date_charge) = MONTH(NOW()) ", nativeQuery = true)
	public Integer getChargesOutcomes();

}
