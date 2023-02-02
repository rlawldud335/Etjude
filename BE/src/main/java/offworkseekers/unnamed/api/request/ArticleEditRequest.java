package offworkseekers.unnamed.api.request;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleEditRequest {
    String userId;
    Long articleId;
    String articleTitle;
    String articleContent;

    @Builder
    public ArticleEditRequest(Long articleId, String articleTitle, String articleContent) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
    }
}
