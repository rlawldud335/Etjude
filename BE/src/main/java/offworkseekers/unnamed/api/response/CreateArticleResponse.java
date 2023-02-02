package offworkseekers.unnamed.api.response;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateArticleResponse {
    Long articleId;
    String articleContent;
    String articleTitle;
    String articleThumbnailUrl;

    public CreateArticleResponse(Long articleId, String articleContent, String articleTitle, String articleThumbnailUrl) {
        this.articleId = articleId;
        this.articleContent = articleContent;
        this.articleTitle = articleTitle;
        this.articleThumbnailUrl = articleThumbnailUrl;
    }
}