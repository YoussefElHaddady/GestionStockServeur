package ma.tc.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.tc.projects.entity.LigneCmdClient;

@Repository
public interface LigneCmdClientRepository extends JpaRepository<LigneCmdClient, Long>{

}
