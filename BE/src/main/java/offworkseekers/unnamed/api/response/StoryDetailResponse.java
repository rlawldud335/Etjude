package offworkseekers.unnamed.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class StoryDetailResponse {

    private String storyVideoUrl;
    private String categoryName;
    private String workTitle;
    private String storyTitle;
    private String storySummary;
    private int storyLikeCount;
    private int studioStack;
    private Long studioId;

    @Builder
    public StoryDetailResponse(String storyVideoUrl, String categoryName, String workTitle, String storyTitle, String storySummary, int storyLikeCount, int studioStack, Long studioId) {
        this.storyVideoUrl = storyVideoUrl;
        this.categoryName = categoryName;
        this.workTitle = workTitle;
        this.storyTitle = storyTitle;
        this.storySummary = storySummary;
        this.storyLikeCount = storyLikeCount;
        this.studioStack = studioStack;
        this.studioId = studioId;
    }
}
