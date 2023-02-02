package offworkseekers.unnamed.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StudioRecordListResponse {

    private Long sceneId;
    private int sceneNumber;
    private String recordVideoUrl;
    private String userId;

    @Builder
    public StudioRecordListResponse(Long sceneId, int sceneNumber, String recordVideoUrl, String userId) {
        this.sceneId = sceneId;
        this.sceneNumber = sceneNumber;
        this.recordVideoUrl = recordVideoUrl;
        this.userId = userId;
    }
}
