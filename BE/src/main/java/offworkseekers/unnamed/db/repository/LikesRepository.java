package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query(value = "select l from Likes l where l.division = :division and l.articleStoryId = :story_id and l.user.userId = :user_id")
    Optional<Likes> findLikeConnection(@Param("story_id") int storyId, @Param("division") int division, @Param("user_id") String userId);

    @Query(value = "select l from Likes l where l.division = :division and l.articleStoryId = :article_id and l.user.userId = :user_id")
    Optional<Likes> findArticleLikeConnection(@Param("article_id") int articleId, @Param("division") int division, @Param("user_id") String userId);
}
