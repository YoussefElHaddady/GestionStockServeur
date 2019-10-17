package ma.tc.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.LigneCmdFournisseur;

@Repository
public interface LigneCmdFournisseurRepository extends JpaRepository<LigneCmdFournisseur, Long>{

}
