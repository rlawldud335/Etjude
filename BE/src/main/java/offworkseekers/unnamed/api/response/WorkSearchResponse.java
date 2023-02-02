package offworkseekers.unnamed.api.response;

import lombok.Data;

@Data
public class WorkSearchResponse {
    Long workId;
    String workTitle;
    String categoryName;

    public WorkSearchResponse(Long workId, String workTitle, String categoryName) {
        this.workId = workId;
        this.workTitle = workTitle;
        this.categoryName = categoryName;
    }
}
