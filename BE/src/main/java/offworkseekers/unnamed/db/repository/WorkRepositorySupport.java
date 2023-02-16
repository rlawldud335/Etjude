package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.api.response.GetWorkResponse;
import offworkseekers.unnamed.api.response.StoriesOfWork;
import offworkseekers.unnamed.api.response.WorkListWithTotalCountResponse;
import offworkseekers.unnamed.api.response.WorkOrderByRandomResponse;

import java.util.List;

public interface WorkRepositorySupport {

    // 조회용 쿼리 인터페이스만 작성, Impl에서 구현, JPARepository로 만들 수 없는 쿼리만..?

    List<WorkOrderByRandomResponse> getWorkListRandom(int pageNum);
    WorkListWithTotalCountResponse getWorkSearchList(String keyword, Long categoryId, int pageNum);
    GetWorkResponse getWork(Long workId);
    List<StoriesOfWork> getStoriesByWorkId(Long workId);
}
