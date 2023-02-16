package offworkseekers.unnamed.api.response;

import lombok.Data;

@Data
public class GetWorkResponse {
    Long workId;
    String workThumbnailUrl;
    String workTitle;
    String workDesc;

    public GetWorkResponse(Long workId, String workThumbnailUrl, String workTitle, String workDesc) {
        this.workId = workId;
        this.workThumbnailUrl = workThumbnailUrl;
        this.workTitle = workTitle;
        this.workDesc = workDesc;
    }
}
