package offworkseekers.unnamed.api.response;

import lombok.Data;

@Data
public class WorkSearchResponse {
    Long workId;
    String workTitle;
    String categoryName;
    String workThumbnailUrl;

    public WorkSearchResponse(Long workId, String workTitle, String categoryName, String workThumbnailUrl) {
        this.workId = workId;
        this.workTitle = workTitle;
        this.categoryName = categoryName;
        this.workThumbnailUrl = workThumbnailUrl;
    }
}
