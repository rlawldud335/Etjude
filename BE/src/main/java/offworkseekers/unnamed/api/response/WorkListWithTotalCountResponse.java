package offworkseekers.unnamed.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class WorkListWithTotalCountResponse {

    private int totalCount;
    private List<WorkSearchResponse> workSearchResponses;

    @Builder
    public WorkListWithTotalCountResponse(int totalCount, List<WorkSearchResponse> workSearchResponses) {
        this.totalCount = totalCount;
        this.workSearchResponses = workSearchResponses;
    }
}
