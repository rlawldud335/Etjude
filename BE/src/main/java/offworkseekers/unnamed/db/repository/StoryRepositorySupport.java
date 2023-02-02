package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.api.response.StoryDetailResponse;
import offworkseekers.unnamed.api.response.StoryListResponse;

import java.util.List;

public interface StoryRepositorySupport {

    List<StoryListResponse> getStoryListRecommendedByLike();

    StoryDetailResponse getStoryDetail(Long storyId);

    List<StoryListResponse> getStorySearchList(String keyword, String category);
}
