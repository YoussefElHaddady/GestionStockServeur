package ma.tc.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long>{

}
