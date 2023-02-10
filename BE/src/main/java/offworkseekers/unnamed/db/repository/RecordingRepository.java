package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Recording;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordingRepository extends JpaRepository<Recording, Long>, RecordingRepositorySupport {
}
