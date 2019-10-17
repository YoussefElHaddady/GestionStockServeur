package ma.tc.projects.repository;

import ma.tc.projects.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findByUsername(String username);
    public boolean existsByUsername(String username);
}
