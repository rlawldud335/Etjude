package offworkseekers.unnamed.api.dto;

import lombok.Getter;

@Getter
public class MyPageLikesStoriesDto {

    Long storyId;
    String storyThumbnailUrl;
    String categoryName;
    String workName;
    Long storyLikeCount;

    public MyPageLikesStoriesDto(Long storyId, String storyThumbnailUrl, String categoryName, String workName, Long storyLikeCount) {
        this.storyId = storyId;
        this.storyThumbnailUrl = storyThumbnailUrl;
        this.categoryName = categoryName;
        this.workName = workName;
        this.storyLikeCount = storyLikeCount;
    }
}
