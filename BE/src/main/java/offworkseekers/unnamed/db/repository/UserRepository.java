package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>, UserRepositorySupport {

}
