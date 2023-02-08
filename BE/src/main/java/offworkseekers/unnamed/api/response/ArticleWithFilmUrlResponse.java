package offworkseekers.unnamed.api.response;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Getter
public class ArticleWithFilmUrlResponse {

    Long articleId;
    Long filmId;
    String articleThumbnailUrl;
    String articleTitle;
    String writerName;
    String writerPhotoUrl;
    LocalDate createdDate;
    int articleLikeCount;

    public ArticleWithFilmUrlResponse(Long articleId, Long filmId, String articleThumbnailUrl, String articleTitle, String writerName, String writerPhotoUrl, LocalDate createdDate, int articleLikeCount) {
        this.articleId = articleId;
        this.filmId = filmId;
        this.articleThumbnailUrl = articleThumbnailUrl;
        this.articleTitle = articleTitle;
        this.writerName = writerName;
        this.writerPhotoUrl = writerPhotoUrl;
        this.createdDate = createdDate;
        this.articleLikeCount = articleLikeCount;
    }

    public void addArticleLikeCount(int likeCount){
        this.articleLikeCount = likeCount;
    }
}
