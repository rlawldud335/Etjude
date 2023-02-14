package offworkseekers.unnamed.api.response;

import lombok.Data;

@Data
public class StoriesOfWork {
    Long storyId;
    String storyThumbnailUrl;
    String storyTitle;
    String workTitle;
    String categoryName;
    Long likeCount;

    public StoriesOfWork(Long storyId, String storyThumbnailUrl, String storyTitle, String workTitle, String categoryName, Long likeCount) {
        this.storyId = storyId;
        this.storyThumbnailUrl = storyThumbnailUrl;
        this.storyTitle = storyTitle;
        this.workTitle = workTitle;
        this.categoryName = categoryName;
        this.likeCount = likeCount;
    }
}
