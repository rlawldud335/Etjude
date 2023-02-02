package offworkseekers.unnamed.api.controller;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.response.GetWorkResponse;
import offworkseekers.unnamed.api.response.StoriesOfWork;
import offworkseekers.unnamed.api.response.WorkOrderByRandomResponse;
import offworkseekers.unnamed.api.response.WorkSearchResponse;
import offworkseekers.unnamed.api.service.WorkService;
import offworkseekers.unnamed.db.entity.Work;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @GetMapping("/api/v1/work/recommended-random")
    public List<WorkOrderByRandomResponse> getWorkListRandom(){
        List<WorkOrderByRandomResponse> workList = workService.getWorkListRandom();
        return workList;
    }

    @GetMapping("/api/v1/work/search")
    public List<WorkSearchResponse> getWorkSearchList(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "category_id") Long categoryId){
        return workService.getWorkSearchList(keyword, categoryId);
    }

    @GetMapping("/api/v1/work/detail")
    public GetWorkResponse getWork(@RequestParam(value = "work_id") Long workId){
        return workService.getWork(workId);
    }

    @GetMapping("/api/v1/work/detail/stories")
    public List<StoriesOfWork> getStoriesByWorkId(@RequestParam(value = "work_id") Long workId){
        return workService.getStoriesByWorkId(workId);
    }
}
