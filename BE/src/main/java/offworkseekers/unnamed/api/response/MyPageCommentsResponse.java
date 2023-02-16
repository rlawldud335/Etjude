package offworkseekers.unnamed.api.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyPageCommentsResponse {
    String userId;
    String userPhotoUrl;
    String content;
    LocalDateTime commentCreateDate;
    Long articleId;

    public MyPageCommentsResponse(String userId, String userPhotoUrl, String content, LocalDateTime commentCreateDate, Long articleId) {
        this.userId = userId;
        this.userPhotoUrl = userPhotoUrl;
        this.content = content;
        this.commentCreateDate = commentCreateDate;
        this.articleId = articleId;
    }
}
