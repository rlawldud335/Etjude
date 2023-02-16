package offworkseekers.unnamed.api.controller;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.response.GetWorkResponse;
import offworkseekers.unnamed.api.response.StoriesOfWork;
import offworkseekers.unnamed.api.response.WorkListWithTotalCountResponse;
import offworkseekers.unnamed.api.response.WorkOrderByRandomResponse;
import offworkseekers.unnamed.api.service.WorkService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @GetMapping("/api/v1/work/recommended-random")
    public List<WorkOrderByRandomResponse> getWorkListRandom(@Param(value = "pageNum") String pageNum){
        List<WorkOrderByRandomResponse> workList = new ArrayList<>();

        if(pageNum==null || pageNum.equals("")){
            workList = workService.getWorkListRandom(1);
        }
        else workList = workService.getWorkListRandom(Integer.parseInt(pageNum));

        return workList;
    }

    @GetMapping("/api/v1/work/search")
    public WorkListWithTotalCountResponse getWorkSearchList(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "category_id") String categoryId, @Param(value = "pageNum") String pageNum){

        int pageNumToInt = 1;
        if(pageNum != null && !pageNum.equals("")) pageNumToInt = Integer.parseInt(pageNum);

        if(categoryId == null || categoryId.equals("")){
            return workService.getWorkSearchList(keyword, 0L, pageNumToInt);
        } else {
            return workService.getWorkSearchList(keyword, Long.parseLong(categoryId), pageNumToInt);
        }

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
