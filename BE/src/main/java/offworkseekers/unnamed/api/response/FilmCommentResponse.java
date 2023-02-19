package offworkseekers.unnamed.api.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilmCommentResponse {
    Long commentId;
    String commentContents;
    LocalDateTime commentCreateTime;
    String userId;
    String userNickname;
    String UserPicture;

    @Builder
    public FilmCommentResponse(Long commentId, String commentContents, LocalDateTime commentCreateTime, String userId, String userNickname, String userPicture) {
        this.commentId = commentId;
        this.commentContents = commentContents;
        this.commentCreateTime = commentCreateTime;
        this.userId = userId;
        this.userNickname = userNickname;
        UserPicture = userPicture;
    }
}
