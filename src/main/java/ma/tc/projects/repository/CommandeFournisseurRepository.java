package ma.tc.projects.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.CommandeFournisseur;

@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Long>{

	public Optional<CommandeFournisseur> findByCodeCmdF(String codeCmd);
}
