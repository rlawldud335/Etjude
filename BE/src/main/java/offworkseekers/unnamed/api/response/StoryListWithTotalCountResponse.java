package offworkseekers.unnamed.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class StoryListWithTotalCountResponse {

    private int totalCount;
    private List<StoryListResponse> storyListResponses;

    @Builder
    public StoryListWithTotalCountResponse(int totalCount, List<StoryListResponse> storyListResponses) {
        this.totalCount = totalCount;
        this.storyListResponses = storyListResponses;
    }
}
