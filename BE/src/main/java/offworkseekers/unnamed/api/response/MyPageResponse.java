package offworkseekers.unnamed.api.response;

import lombok.Getter;
import offworkseekers.unnamed.api.dto.MyPageFollowDto;

import java.util.List;

@Getter
public class MyPageResponse {

    MyPageSimpleResponse myPageSimpleResponse;
    int followerCount;
    int followingCount;
    List<MyPageFollowDto> followings;
    List<MyPageFollowDto> followers;

    public MyPageResponse(MyPageSimpleResponse myPageSimpleResponse, int followerCount, int followingCount, List<MyPageFollowDto> followings, List<MyPageFollowDto> followers) {
        this.myPageSimpleResponse = myPageSimpleResponse;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.followings = followings;
        this.followers = followers;
    }
}
