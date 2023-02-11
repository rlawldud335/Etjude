package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.api.response.StoryDetailResponse;
import offworkseekers.unnamed.api.response.StoryListResponse;

import java.util.List;

public interface StoryRepositorySupport {

    List<StoryListResponse> getStoryListRecommendedByLike(int pageNum);

    StoryDetailResponse getStoryDetail(Long storyId, String userId);

    List<StoryListResponse> getStorySearchList(String keyword, Long categoryId, int pageNum);
}
