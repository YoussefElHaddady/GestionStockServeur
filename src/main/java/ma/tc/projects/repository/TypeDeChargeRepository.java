package ma.tc.projects.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.TypeDeCharge;

@Repository
public interface TypeDeChargeRepository extends JpaRepository<TypeDeCharge, Long>{

	Optional<TypeDeCharge> findByLibele(String libele);
    Boolean existsByLibele(String libele);
}
