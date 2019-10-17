package ma.tc.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.ReglementFournisseur;

@Repository
public interface ReglementFournisseurRepository extends JpaRepository<ReglementFournisseur, Long> {

}
