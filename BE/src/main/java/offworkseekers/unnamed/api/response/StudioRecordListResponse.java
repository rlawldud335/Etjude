package offworkseekers.unnamed.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StudioRecordListResponse {

    private Long sceneId;
    private int sceneNumber;
    private String recordVideoUrl;
    private String nickname;

    @JsonProperty(value = "profile_url")
    private String profileURL;

    @Builder
    public StudioRecordListResponse(Long sceneId, int sceneNumber, String recordVideoUrl, String nickname, String profileURL) {
        this.sceneId = sceneId;
        this.sceneNumber = sceneNumber;
        this.recordVideoUrl = recordVideoUrl;
        this.nickname = nickname;
        this.profileURL = profileURL;
    }
}
