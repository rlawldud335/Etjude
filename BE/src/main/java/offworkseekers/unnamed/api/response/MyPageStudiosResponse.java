package offworkseekers.unnamed.api.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class MyPageStudiosResponse {

    Long studioId;
    String storyThumbnailUrl;
    String studioTitle;
    String storyTitle;
    @Setter
    LocalDate studioCreatedDate;
    LocalDate studioEndDate;

    public MyPageStudiosResponse(Long studioId, String storyThumbnailUrl, String studioTitle, String storyTitle, LocalDate studioCreatedDate, LocalDate studioEndDate) {
        this.studioId = studioId;
        this.storyThumbnailUrl = storyThumbnailUrl;
        this.studioTitle = studioTitle;
        this.storyTitle = storyTitle;
        this.studioCreatedDate = studioCreatedDate;
        this.studioEndDate = studioEndDate;
    }
}
