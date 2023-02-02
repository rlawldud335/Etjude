package offworkseekers.unnamed.api.response;

import lombok.Getter;
import offworkseekers.unnamed.api.dto.MyPageLikesArticlesDto;
import offworkseekers.unnamed.api.dto.MyPageLikesStoriesDto;

import java.util.List;

@Getter
public class MyPageLikesResponse {

    List<MyPageLikesArticlesDto> articles;
    List<MyPageLikesStoriesDto> stories;

    public MyPageLikesResponse(List<MyPageLikesArticlesDto> articles, List<MyPageLikesStoriesDto> stories) {
        this.articles = articles;
        this.stories = stories;
    }
}
