package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.api.response.GetWorkResponse;
import offworkseekers.unnamed.api.response.StoriesOfWork;
import offworkseekers.unnamed.api.response.WorkOrderByRandomResponse;
import offworkseekers.unnamed.api.response.WorkSearchResponse;
import offworkseekers.unnamed.db.entity.Work;

import java.util.List;

public interface WorkRepositorySupport {

    // 조회용 쿼리 인터페이스만 작성, Impl에서 구현, JPARepository로 만들 수 없는 쿼리만..?

    List<WorkOrderByRandomResponse> getWorkListRandom();
    List<WorkSearchResponse> getWorkSearchList(String keyword, Long categoryId);
    GetWorkResponse getWork(Long workId);
    List<StoriesOfWork> getStoriesByWorkId(Long workId);
}
