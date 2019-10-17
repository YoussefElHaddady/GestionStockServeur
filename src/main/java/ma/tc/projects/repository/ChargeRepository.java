package ma.tc.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.Charge;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long>{

}
