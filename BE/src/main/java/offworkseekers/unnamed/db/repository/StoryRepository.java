package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long>, StoryRepositorySupport {

}
