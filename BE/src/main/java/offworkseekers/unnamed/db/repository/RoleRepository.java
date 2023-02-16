package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositorySupport {

}
