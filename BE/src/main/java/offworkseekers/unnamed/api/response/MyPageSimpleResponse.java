package offworkseekers.unnamed.api.response;

import lombok.Getter;

@Getter
public class MyPageSimpleResponse {

    String userPhotoUrl;
    String userNickName;

    public MyPageSimpleResponse(String userPhotoUrl, String userNickName) {
        this.userPhotoUrl = userPhotoUrl;
        this.userNickName = userNickName;
    }
}
