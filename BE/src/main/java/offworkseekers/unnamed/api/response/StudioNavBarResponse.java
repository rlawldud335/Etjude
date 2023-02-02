package offworkseekers.unnamed.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StudioNavBarResponse {

    private String studioTitle;
    private String userId;
    private String nickname;
    private String userPhotoUrl;

    @Builder
    public StudioNavBarResponse(String studioTitle, String userId, String nickname, String userPhotoUrl) {
        this.studioTitle = studioTitle;
        this.userId = userId;
        this.nickname = nickname;
        this.userPhotoUrl = userPhotoUrl;
    }
}
