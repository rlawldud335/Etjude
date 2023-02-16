package offworkseekers.unnamed.api.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StudioIdWithUserIdRequest {

    @JsonProperty(value = "studio_id")
    private Long studioId;

    @JsonProperty(value = "user_id")
    private String userId;

    public StudioIdWithUserIdRequest(Long studioId, String userId) {
        this.studioId = studioId;
        this.userId = userId;
    }
}
