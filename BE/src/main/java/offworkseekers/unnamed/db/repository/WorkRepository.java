package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.api.response.WorkSearchResponse;
import offworkseekers.unnamed.db.entity.Category;
import offworkseekers.unnamed.db.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long>, WorkRepositorySupport {

//    @Query(value = "SELECT * FROM work order by RAND()",nativeQuery = true)
//    List<Work> findAll();

//    @Query("select w from Work w where w.category = :category") // categoryId로 찾아야 하는데..
//    List<Work> findByCategoryAndWorkTitleContains(Long categoryId, String keyword);

//    Work findByWorkId(Long workId);


    
    // 스토리 모아보기 기능은 WorkRepository와 StoryRepository 중 어디에 있어야 하는가


}