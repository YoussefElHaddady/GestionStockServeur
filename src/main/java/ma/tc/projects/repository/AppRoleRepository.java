package ma.tc.projects.repository;

import ma.tc.projects.entity.AppRole;
import ma.tc.projects.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AppRoleRepository extends JpaRepository<AppRole,Long> {


    public AppRole findByRoleName(String roleName);

}
