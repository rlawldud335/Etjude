package offworkseekers.unnamed.api.controller;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.response.*;
import offworkseekers.unnamed.api.service.MyPageService;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @PostMapping("/api/v1/mypage/studio")
    public List<MyPageStudiosResponse> getMyPageStudios(@RequestBody Map<String, String> request){
        return myPageService.getMyPageStudios(request.get("user_id"));
    }

    @PostMapping("/api/v1/mypage/film")
    public List<MyPageFilmsWithMembersResponse> getMyPageFilms(@RequestBody Map<String, String> request){
        return myPageService.getMyPageFilms(request.get("user_id"));
    }

    @PostMapping("/api/v1/mypage/articles")
    public List<MyPageArticlesResponse> getMyPageArticles(@RequestBody Map<String, String> request){
        return myPageService.getMyPageArticle(request.get("user_id"));
    }

    @PostMapping("/api/v1/mypage/mylikes")
    public MyPageLikesResponse getMyPageLikes(@RequestBody Map<String, String> request){
        return myPageService.getMyPageLikes(request.get("user_id"));
    }

    @PostMapping("/api/v1/mypage/comments")
    public List<MyPageCommentsResponse> getMyPageComments(@RequestBody Map<String, String> request){
        return myPageService.getMyPageComments(request.get("user_id"));
    }

    @PostMapping("/api/v1/mypage")
    public MyPageResponse getMyPage(@RequestBody Map<String, String> request){
        return myPageService.getMyPage(request.get("user_id"));
    }
}
