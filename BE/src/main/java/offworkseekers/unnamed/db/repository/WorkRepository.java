package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long>, WorkRepositorySupport {

}