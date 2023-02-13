package offworkseekers.unnamed.api.service;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offworkseekers.unnamed.api.dto.SimpleUserDTO;
import offworkseekers.unnamed.api.request.StudioCreateRequest;
import offworkseekers.unnamed.api.response.*;
import offworkseekers.unnamed.db.entity.*;
import offworkseekers.unnamed.db.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class StudioService {

    private final StudioRepository studioRepository;
    private final RecordingRepository recordingRepository;
    private final RecordingRepositoryImpl recordingRepositoryImpl;
    private final StoryRepository storyRepository;

    private final UserRepository userRepository;
    private final SceneRepository sceneRepository;

    public StudioInfoResponse getStudioInfo(Long studioId) {
        return studioRepository.findStudioInfo(studioId);
    }

    public StudioNavBarResponse getStudioNavbar(Long studioId, String userId){
        return studioRepository.findStudioNavbar(studioId, userId);
    }

    @Transactional
    public void saveStudio(StudioCreateRequest request) {
        Story story = storyRepository.findById(request.getStoryId()).orElse(null);
        Studio studio = request.toStudioEntity();
        studio.connectStory(story);

        List<User> userList = new ArrayList<>();
        List<String> teamMembersIds = request.getTeamMembersIds();
        for (String teamMembersId : teamMembersIds) {
            User user = userRepository.findById(teamMembersId).orElse(null);
            userList.add(user);
        }
        studio.addTeamMember(userList);
        studioRepository.save(studio);
    }

    public List<UserSearchResponse> searchUser(String keyword) {
        return userRepository.findUserSimple(keyword);
    }

    public String getStudioStoryVideoUrl(Long studioId) {
        Studio studio = studioRepository.findById(studioId).orElse(null);
        Story story = storyRepository.findById(studio.getStory().getStoryId()).orElse(null);

        return story.getStoryVideoUrl();
    }

    public StudioSettingResponse getStudioSetting(Long studioId, String userId) {
        StudioSettingResponse studioSetting = studioRepository.findStudioSetting(studioId, userId);
        return studioSetting;
    }

    public List<StudioFilmListResponse> getStudioFilmList(Long studioId) {
        List<Film> studioFilmList = studioRepository.getStudioFilmList(studioId);

        List<StudioFilmListResponse> responses = new ArrayList<>();

        for (Film film : studioFilmList) {
            responses.add(
                    StudioFilmListResponse.builder()
                            .filmId(film.getFilmId())
                            .filmVideoUrl(film.getFilmVideoUrl())
                            .filmCreatedDate(film.getFilmCreatedDate())
                            .filmEndDate(film.getFilmCreatedDate().plusDays(7))
                            .build()
            );
        }
        return responses;
    }

    public List<StudioRecordListResponse> getStoryRecordingList(Long studioId) {
        return studioRepository.findRecordingByStudioId(studioId);
    }

    @Transactional
    public List<Long> getExpiredStudioIds(){
        List<Studio> studioList = studioRepository.findAll();
        List<Long> expiredStudioIds = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (Studio studio : studioList) {
            if(studio.getStudioEndDate().isBefore(now)){
                expiredStudioIds.add(studio.getStudioId());
            }
        }
        return expiredStudioIds;
    }
    
    public void saveRecording(int studioId, int sceneId, String recordingVideoUrl, String userId) {
        Recording recording = recordingRepositoryImpl.findRecordingByStudioIdAndSceneId(studioId, sceneId);
        if (recording != null) {
            recording.changeUrl(recordingVideoUrl);
            recording.changeUserId(userId);
            recordingRepository.save(recording);
        }

        if (recording == null) {
            Studio studio = studioRepository.findById(Long.valueOf(studioId)).orElse(null);
            Scene scene = sceneRepository.findById(Long.valueOf(sceneId)).orElse(null);

            recordingRepository.save(Recording.builder()
                            .recordingVideoUrl(recordingVideoUrl)
                            .userId(userId)
                            .studio(studio)
                            .scene(scene)
                    .build());
        }
    }

}
