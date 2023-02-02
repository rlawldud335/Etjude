package offworkseekers.unnamed.api.controller;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.request.CreateFollowRequest;
import offworkseekers.unnamed.api.service.FollowService;
import offworkseekers.unnamed.db.entity.Follow;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/api/v1/follow")
    public ResponseEntity createFollow(@RequestBody CreateFollowRequest request){

        String myUserId = request.getMyUserId();
        String yourUserId = request.getYourUserId();

        if(followService.getFollowByFollowingFollower(myUserId, yourUserId))
            return ResponseEntity.badRequest().build();

        Follow follow = followService.createFollow(myUserId, yourUserId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/v1/follow")
    public void deleteFollow(@RequestBody CreateFollowRequest request){
        followService.deleteFollow(request.getMyUserId(), request.getYourUserId());
    }

}
