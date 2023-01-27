package offworkseekers.unnamed.api.request;

import lombok.Data;
import lombok.Getter;
import offworkseekers.unnamed.db.entity.Article;

@Data
@Getter
public class ArticleCreateRequest {

    Long articleId;
    String articleContent;
    String articleTitle;
    String articleThumbnailUrl;

    public Article toEntity() {
        return Article.builder()
                .articleId(articleId)
                .articleContent(articleContent)
                .articleTitle(articleTitle)
                .articleThumbnailUrl(articleThumbnailUrl)
                .build();
    }


}
