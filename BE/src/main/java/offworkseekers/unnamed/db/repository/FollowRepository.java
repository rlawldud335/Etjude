package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Follow;
import offworkseekers.unnamed.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    void deleteFollowByFollowerAndFollowing(User follower, User following);
    Follow findByFollowerAndFollowing(User follower, User following);
}
