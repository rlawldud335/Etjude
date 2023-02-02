package offworkseekers.unnamed.api.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PopularFilmResponse {
    Long articleId;
    String articleTitle;
    int articleViewCnt;
    Long filmId;
    String articleThumbnailUrl;
    LocalDate articleCreatedDate;
    String writerName;
    String writerPhotoUrl;
    int likeCount;

    @Builder
    public PopularFilmResponse(Long articleId, String articleTitle, int articleViewCnt, Long filmId, String articleThumbnailUrl, LocalDate articleCreatedDate, String writerName, String writerPhotoUrl) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleViewCnt = articleViewCnt;
        this.filmId = filmId;
        this.articleThumbnailUrl = articleThumbnailUrl;
        this.articleCreatedDate = articleCreatedDate;
        this.writerName = writerName;
        this.writerPhotoUrl = writerPhotoUrl;
    }
}
