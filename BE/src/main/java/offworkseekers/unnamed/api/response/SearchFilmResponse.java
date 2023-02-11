package offworkseekers.unnamed.api.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchFilmResponse {
    Long articleId;
    String articleTitle;
    LocalDate articleCreatedDate;
    String filmThumbnailUrl;
    String writerId;
    String writerName;
    String writerPhotoUrl;

    public SearchFilmResponse(Long articleId, String articleTitle, LocalDate articleCreatedDate, String filmThumbnailUrl, String writerId, String writerName, String writerPhotoUrl) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleCreatedDate = articleCreatedDate;
        this.filmThumbnailUrl = filmThumbnailUrl;
        this.writerId = writerId;
        this.writerName = writerName;
        this.writerPhotoUrl = writerPhotoUrl;
    }
}
