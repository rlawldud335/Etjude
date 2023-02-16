package offworkseekers.unnamed.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class ArticleCreateRequest {

    String userId;
    Long filmId;
    String articleContent;
    String articleTitle;
    String articleThumbnailUrl;

    @Builder
    public ArticleCreateRequest(String userId, Long filmId, String articleContent, String articleTitle, String articleThumbnailUrl) {
        this.userId = userId;
        this.filmId = filmId;
        this.articleContent = articleContent;
        this.articleTitle = articleTitle;
        this.articleThumbnailUrl = articleThumbnailUrl;
    }
}
