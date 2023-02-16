package offworkseekers.unnamed.api.dto;

import lombok.Getter;

@Getter
public class MyPageFollowDto {
    String userId;
    String userPhotoUrl;

    public MyPageFollowDto(String userId, String userPhotoUrl) {
        this.userId = userId;
        this.userPhotoUrl = userPhotoUrl;
    }
}
