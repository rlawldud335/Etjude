package offworkseekers.unnamed.api.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MyPageLikesArticlesDto {

    Long articeId;
    String articleTitle;
    String userId;
    String userPhotoUrl;
    LocalDate articleCreatedDate;

    public MyPageLikesArticlesDto(Long articeId, String articleTitle, String userId, String userPhotoUrl, LocalDate articleCreatedDate) {
        this.articeId = articeId;
        this.articleTitle = articleTitle;
        this.userId = userId;
        this.userPhotoUrl = userPhotoUrl;
        this.articleCreatedDate = articleCreatedDate;
    }
}
