package ma.tc.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.Caisse;

@Repository
public interface CaisseRepository extends JpaRepository<Caisse, Long>{

}
