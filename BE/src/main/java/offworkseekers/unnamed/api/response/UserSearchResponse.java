package offworkseekers.unnamed.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserSearchResponse {

    @JsonProperty(value = "user_id")
    private String userId;
    private String nickname;
    @JsonProperty(value = "user_email")
    private String userEmail;

    @Builder
    public UserSearchResponse(String userId, String nickname, String userEmail) {
        this.userId = userId;
        this.nickname = nickname;
        this.userEmail = userEmail;
    }
}
