package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio, Long>, StudioRepositorySupport {

}
