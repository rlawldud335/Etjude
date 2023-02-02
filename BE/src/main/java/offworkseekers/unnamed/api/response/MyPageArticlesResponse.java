package offworkseekers.unnamed.api.response;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class MyPageArticlesResponse {
    Long articleId;
    String articleTitle;
    String userId;
    String userPhotoUrl;
    int articleViewCount;
    LocalDate articleCreatedDate;

    public MyPageArticlesResponse(Long articleId, String articleTitle, String userId, String userPhotoUrl, int articleViewCount, LocalDate articleCreatedDate) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.userId = userId;
        this.userPhotoUrl = userPhotoUrl;
        this.articleViewCount = articleViewCount;
        this.articleCreatedDate = articleCreatedDate;
    }
}
