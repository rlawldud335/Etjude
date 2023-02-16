package offworkseekers.unnamed.api.service;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.db.entity.Follow;
import offworkseekers.unnamed.db.repository.FollowRepository;
import offworkseekers.unnamed.db.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public Follow createFollow(String myUserId, String yourUserId){

        Follow follow = Follow.builder()
                .follower(userRepository.findById(myUserId).get())
                .following(userRepository.findById(yourUserId).get())
                .build();

        // 이미 있으면 예외처리

        return followRepository.save(follow);
    }

    public void deleteFollow(String myUserId, String yourUserId){

        followRepository.deleteFollowByFollowerAndFollowing(
                userRepository.findById(myUserId).get(), userRepository.findById(yourUserId).get());
    }

    public boolean getFollowByFollowingFollower(String myUserId, String yourUserId){
        Follow follow = followRepository.findByFollowerAndFollowing(userRepository.findById(myUserId).get(), userRepository.findById(yourUserId).get());
        if (follow != null){
            return true;
        } else return false;
    }
}
