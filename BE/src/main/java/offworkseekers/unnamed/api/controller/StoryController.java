package offworkseekers.unnamed.api.controller;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.response.RoleWithLineOfSceneResponse;
import offworkseekers.unnamed.api.response.StoryDetailResponse;
import offworkseekers.unnamed.api.response.StoryListResponse;
import offworkseekers.unnamed.api.response.StoryRoleResponse;
import offworkseekers.unnamed.api.service.StoryService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

    @GetMapping(value = "/api/v1/story/recommended/popular")
    public List<StoryListResponse> storyListByLike(@RequestParam(value = "pageNum") int pageNum) {
        List<StoryListResponse> response = storyService.storyListRecommendedByLike(pageNum);
        return response;
    }

    @PostMapping(value = "/api/v1/story/detail")
    public StoryDetailResponse storyDetail(@RequestBody @Valid Map<String, Object> param) {
        Long storyId = Long.valueOf((int) param.get("story_id"));
        String userId = (String) param.get("user_id");

        StoryDetailResponse storyDetailResponse = storyService.storyDetailResponse(storyId, userId);
        return storyDetailResponse;
    }

    @GetMapping(value = "/api/v1/story/detail/desc")
    public String storyDesc(@RequestParam(value = "story_id") Long storyId) {
        return storyService.storyDescResponse(storyId);
    }

    @GetMapping(value = "/api/v1/story/detail/roles")
    public List<StoryRoleResponse> storyRoles(@RequestParam(value = "story_id") Long storyId) {
        return storyService.storyRoleList(storyId);
    }

    @GetMapping(value = "/api/v1/story/detail/scripts")
    public List<RoleWithLineOfSceneResponse> storyRoleWithScript(@RequestParam(value = "story_id") Long storyId) {
        return storyService.roleWithLineOfSceneResponseList(storyId);
    }

    @GetMapping(value = "/api/v1/story/search")
    public List<StoryListResponse> storySearchList(@RequestParam(value = "keyword") String keyword, @Param(value = "category_id") String categoryId, @RequestParam(value = "pageNum") int pageNum) {;
        System.out.println(categoryId);
        if (categoryId == null || categoryId.equals("")) {
            return storyService.storySearchList(keyword, 0L, pageNum);
        }
        return storyService.storySearchList(keyword, Long.valueOf(categoryId), pageNum);
    }

    @PostMapping(value = "/api/v1/story/like")
    public ResponseEntity storyLike(@RequestBody @Valid Map<String, Object> param) {

        Object storyId =  param.get("story_id");
        if (storyId == null || !(storyId instanceof Integer)) {
            return ResponseEntity.badRequest().build();
        }

        Object userId =  param.get("user_id");
        if (!(userId instanceof String)) {
            return ResponseEntity.badRequest().build();
        } else if (userId instanceof String && ((String) userId).replaceAll(" ", "") == ""){
            return ResponseEntity.badRequest().build();
        }

        Object division =  param.get("division");
        if (division == null || !(division instanceof Integer)) {
            return ResponseEntity.badRequest().build();
        } else if (division instanceof String && ((Integer) division) != 1){
            return ResponseEntity.badRequest().build();
        }

        storyService.editStoryLike((int) storyId, (int) division, (String) userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/api/v1/story/like")
    public ResponseEntity storyLikeCancel(@RequestBody @Valid Map<String, Object> param) {
        if (param.get("story_id") == null || !(param.get("story_id") instanceof Integer)) {
            return ResponseEntity.badRequest().build();
        }

        if (!(param.get("user_id") instanceof String)) {
            return ResponseEntity.badRequest().build();
        } else if (param.get("user_id") instanceof String && ((String) param.get("user_id")).replaceAll(" ", "") == ""){
            return ResponseEntity.badRequest().build();
        }

        if (param.get("division") == null || !(param.get("division") instanceof Integer)) {
            return ResponseEntity.badRequest().build();
        } else if (param.get("division") instanceof String && ((Integer) param.get("division")) != 1){
            return ResponseEntity.badRequest().build();
        }

        int storyId = (int) param.get("story_id");
        int division = (int) param.get("division");
        String userId = (String) param.get("user_id");

        storyService.deleteStoryLike(storyId, division, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/api/v1/story/like")
    public boolean storyLikeStatus(@RequestBody @Valid Map<String, Object> param) {
        int storyId = (int) param.get("story_id");
        int division = (int) param.get("division");
        String userId = (String) param.get("user_id");

        if (storyService.getStoryLikeStatus(storyId, division, userId).isEmpty()) {
            return false;
        }
        return true;
    }

}
