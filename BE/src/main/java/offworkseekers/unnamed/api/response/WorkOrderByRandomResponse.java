package offworkseekers.unnamed.api.response;

import lombok.Data;

@Data
public class WorkOrderByRandomResponse {

    Long workId;
    String workThumbnailUrl;
    String categoryName;
    String workTitle;

    public WorkOrderByRandomResponse(Long workId, String workThumbnailUrl, String categoryName, String workTitle) {
        this.workId = workId;
        this.workThumbnailUrl = workThumbnailUrl;
        this.categoryName = categoryName;
        this.workTitle = workTitle;
    }
}
