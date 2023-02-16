package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Comment c set c.commentContents=:commentContents, c.commentCreateTime=CURRENT_TIMESTAMP where c.commentId=:commentId")
    void updateComment(Long commentId, String commentContents);
}
