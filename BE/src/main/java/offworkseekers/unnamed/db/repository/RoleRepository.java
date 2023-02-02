package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositorySupport {

}
