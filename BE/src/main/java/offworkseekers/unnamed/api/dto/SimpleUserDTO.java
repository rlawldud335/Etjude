package offworkseekers.unnamed.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SimpleUserDTO {

    @JsonProperty(value = "user_id")
    private String userId;

    @JsonProperty(value = "nickname")
    private String nickName;

    @JsonProperty(value = "profile_url")
    private String picture;

    @Builder
    public SimpleUserDTO(String userId, String nickName, String picture) {
        this.userId = userId;
        this.nickName = nickName;
        this.picture = picture;
    }
}
