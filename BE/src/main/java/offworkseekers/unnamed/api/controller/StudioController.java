package offworkseekers.unnamed.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offworkseekers.unnamed.api.dto.SocketDTO;
import offworkseekers.unnamed.api.dto.StatusDTO;
import offworkseekers.unnamed.api.request.StudioCreateRequest;
import offworkseekers.unnamed.api.request.StudioIdWithUserIdRequest;
import offworkseekers.unnamed.api.response.*;
import offworkseekers.unnamed.api.service.StoryService;
import offworkseekers.unnamed.api.service.StudioService;
import offworkseekers.unnamed.db.entity.Studio;
import offworkseekers.unnamed.db.entity.User;
import offworkseekers.unnamed.db.repository.StudioRepository;
import offworkseekers.unnamed.db.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StudioController {


    private final StudioService studioService;
    private final StoryService storyService;

    private final StudioRepository studioRepository;
    private final UserRepository userRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;


    @PostMapping(value = "/api/v1/studio/info")
    public StudioInfoResponse getStudioInfo(@RequestBody @Valid Map<String, Long>param) {
        Long studioId = param.get("studio_id");
        return studioService.getStudioInfo(studioId);
    }

    @PostMapping(value = "/api/v1/studio/navbar")
    public StudioNavBarResponse getStudioNavbar(@RequestBody @Valid StudioIdWithUserIdRequest request) {
        log.debug("request = {}", request);
        return studioService.getStudioNavbar(request.getStudioId(), request.getUserId());
    }


    @PostMapping(value = "/api/v1/studio/create")
    public void createStudio(@RequestBody @Valid StudioCreateRequest studioCreateRequest) {
        studioService.saveStudio(studioCreateRequest);
    }

    @PostMapping(value = "/api/v1/studio/search")
    public List<UserSearchResponse> searchUser(@RequestBody @Valid Map<String, String> param){
        String keyword = param.get("keyword");
        return studioService.searchUser(keyword);
    }

    @PostMapping(value = "/api/v1/studio/story/video")
    public String getStudioVideoUrl(@RequestBody @Valid Map<String, Long> param) {
        Long studioId = param.get("studio_id");
        return studioService.getStudioStoryVideoUrl(studioId);
    }

    /**
     * studio ID로 studio 를  조회하므로 service layer 로의 이동이 불필요하다고 판단하여,
     * StudioController 에서 직접 StudioRepository 를 참조하여 studio 를 조회해옴.
     */
    @PostMapping(value = "/api/v1/studio/story/scripts")
    public List<RoleWithLineOfSceneResponse> getStudioScripts(@RequestBody @Valid Map<String, Long> param) {
        Long studioId = param.get("studio_id");
        Studio studio = studioRepository.findById(studioId).orElse(null);
        Long storyId = studio.getStory().getStoryId();
        return storyService.roleWithLineOfSceneResponseList(storyId);
    }

    @PostMapping(value = "/api/v1/studio/scripts")
    public StudioSettingResponse getStudioSetting(@RequestBody @Valid StudioIdWithUserIdRequest studioIdWithUserIdRequest) {
        StudioSettingResponse studioSetting = studioService.getStudioSetting(studioIdWithUserIdRequest.getStudioId(), studioIdWithUserIdRequest.getUserId());
        return studioSetting;
    }

    @PostMapping(value = "/api/v1/studio/film")
    public List<StudioFilmListResponse> getStudioFilmList(@RequestBody @Valid Map<String, Long> param) {
        Long studioId = param.get("studio_id");
        return studioService.getStudioFilmList(studioId);
    }

    @PostMapping(value = "/api/v1/studio/recording")
    public List<StudioRecordListResponse> getStudioRecordList(@RequestBody @Valid Map<String, Long> param){
        Long studioId = param.get("studio_id");
        return studioService.getStoryRecordingList(studioId);
    }

    @MessageMapping("/api/v1/studio/chat/{studioId}/{userId}/{nickname}")
    public void chat(@DestinationVariable("studioId") String studioId,
                     @DestinationVariable("userId") String userId,
                     @DestinationVariable("nickname") String nickname, String content) throws Exception{

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분");
        String formattedNow = now.format(formatter);

        simpMessagingTemplate.convertAndSend("/sub/api/v1/studio/chat/" + studioId , new SocketDTO(studioId, userId, nickname, content, formattedNow));
    }

    /**
     * user ID로 user 를 조회하므로 service layer 로의 이동이 불필요하다고 판단하여,
     * StudioController 에서 UserRepository 를 직접 참조하여 user 를 조회해옴.
     */
    @MessageMapping("/api/v1/studio/chat/status/{studioId}/{sceneNumber}/{userId}/{status}")
    public void recordingStart(@DestinationVariable("studioId") String studioId,
                               @DestinationVariable("sceneNumber") String sceneNumber,
                               @DestinationVariable("userId") String userId,
                               @DestinationVariable("status") String status) throws Exception {

        User user = userRepository.findById(userId).orElse(null);

        simpMessagingTemplate.convertAndSend("/status/api/v1/studio/chat/" + studioId, StatusDTO.builder()
                .user(user)
                .sceneNumber(Integer.parseInt(sceneNumber))
                .status(status)
                .build());
    }

    @PutMapping(value = "/api/v1/studio/recording")
    public ResponseEntity saveRecording (@RequestBody @Valid Map<String, Object> param){
        int studioId = (int) param.get("studio_id");
        int sceneId = (int) param.get("scene_id");
        String userId = (String) param.get("user_id");
        String recordingVideoUrl = (String) param.get("recording_video_url");

        studioService.saveRecording(studioId, sceneId, recordingVideoUrl, userId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
