package offworkseekers.unnamed.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.dto.SimpleUserDTO;
import offworkseekers.unnamed.api.response.StudioInfoResponse;
import offworkseekers.unnamed.api.response.StudioNavBarResponse;
import offworkseekers.unnamed.api.response.StudioRecordListResponse;
import offworkseekers.unnamed.api.response.StudioSettingResponse;
import offworkseekers.unnamed.db.entity.Film;
import offworkseekers.unnamed.db.entity.Scene;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static offworkseekers.unnamed.db.entity.QFilm.film;
import static offworkseekers.unnamed.db.entity.QRecording.recording;
import static offworkseekers.unnamed.db.entity.QScene.scene;
import static offworkseekers.unnamed.db.entity.QStudio.studio;
import static offworkseekers.unnamed.db.entity.QTeamMember.teamMember;
import static offworkseekers.unnamed.db.entity.QUser.user;

@RequiredArgsConstructor
public class StudioRepositoryImpl implements StudioRepositorySupport{

    private final JPAQueryFactory queryFactory;

    @Override
    public StudioInfoResponse findStudioInfo(Long studioId) {
        Tuple studioInfo = queryFactory
                .select(studio.studioId,
                        studio.studioTitle,
                        studio.studioEndDate,
                        studio.story.storyId,
                        studio.story.storyTitle,
                        studio.story.storyVideoUrl,
                        studio.captainId
                )
                .from(studio)
                .where(
                        studio.studioId.eq(studioId)
                )
                .fetchOne();


        List<Tuple> members = queryFactory
                .select(teamMember.user.userId,
                        teamMember.user.nickName,
                        teamMember.user.picture
                )
                .from(teamMember)
                .where(
                        teamMember.studio.studioId.eq(studioId)
                )
                .fetch();

        List<SimpleUserDTO> memberList = new ArrayList<>();
        for (Tuple member : members) {
            memberList.add(
                    SimpleUserDTO.builder()
                    .userId(member.get(teamMember.user.userId))
                    .nickName(member.get(teamMember.user.nickName))
                    .picture(member.get(teamMember.user.picture))
                    .build()
            );
        }

        return StudioInfoResponse.builder()
                .studioId(studioInfo.get(studio.studioId))
                .studioTitle(studioInfo.get(studio.studioTitle))
                .studioCreateDate(studioInfo.get(studio.studioEndDate).minusDays(7))
                .studioEndDate(studioInfo.get(studio.studioEndDate))
                .storyId(studioInfo.get(studio.story.storyId))
                .storyTitle(studioInfo.get(studio.story.storyTitle))
                .storyVideoUrl(studioInfo.get(studio.story.storyVideoUrl))
                .captainId(studioInfo.get(studio.captainId))
                .memberList(memberList)
                .build();
    }

    @Override
    public StudioNavBarResponse findStudioNavbar(Long studioId, String userId) {
        Tuple tuple = queryFactory
                .select(studio.studioTitle, user.nickName, user.picture)
                .from(studio, user)
                .where(studio.studioId.eq(studioId),
                        user.userId.eq(userId))
                .fetchOne();

        return StudioNavBarResponse.builder()
                .userId(userId)
                .studioTitle(tuple.get(studio.studioTitle))
                .nickname(tuple.get(user.nickName))
                .userPhotoUrl(tuple.get(user.picture))
                .build();
    }

    @Override
    public List<Film> getStudioFilmList(Long studioId) {
        List<Film> fetch = queryFactory
                .selectFrom(film)
                .where(film.studio.studioId.eq(studioId))
                .fetch();
        return fetch;
    }

    @Override
    public List<StudioRecordListResponse> findRecordingByStudioId(Long studioId, Long storyId) {
        System.out.println(studioId);
        List<Scene> storySceneList = queryFactory
                .selectFrom(scene)
                .where(
                        scene.story.storyId.eq(storyId)
                )
                .fetch();

        List<StudioRecordListResponse> studioRecordListResponseList = new ArrayList<>();
        for (Scene nowScene : storySceneList) {
            Long nowSceneId = nowScene.getSceneId();
            int nowSceneNumber = nowScene.getSceneNumber();
            System.out.println(nowSceneId);

            Tuple tuple = queryFactory
                    .select(recording.recordingVideoUrl, recording.scene.sceneId, recording.scene.sceneNumber, recording.userId)
                    .from(recording)
                    .where(
                            recording.scene.sceneId.eq(nowSceneId),
                            recording.studio.studioId.eq(studioId)
                    )
                    .fetchOne();

            String recordingNickname = null;
            String recordingUserProfileUrl = null;
            String recordingVideoUrl = null;

            if (tuple != null) {
                Tuple userInfo = queryFactory
                        .select(user.nickName, user.picture)
                        .from(user)
                        .where(
                                user.userId.eq(tuple.get(recording.userId))
                        )
                        .fetchOne();
                recordingNickname = userInfo.get(user.nickName);
                recordingUserProfileUrl = userInfo.get(user.picture);
                recordingVideoUrl = tuple.get(recording.recordingVideoUrl);
            }


            studioRecordListResponseList.add(
                    StudioRecordListResponse.builder()
                            .recordVideoUrl(recordingVideoUrl)
                            .sceneId(nowSceneId)
                            .sceneNumber(nowSceneNumber)
                            .nickname(recordingNickname)
                            .profileURL(recordingUserProfileUrl)
                            .build()
            );
        }
        return studioRecordListResponseList;
    }

    @Override
    public StudioSettingResponse findStudioSetting(Long studioId, String userId) {
        Tuple tuple = queryFactory
                .select(studio.studioTitle, studio.studioEndDate, user.nickName, user.picture)
                .from(studio, user)
                .where(
                        studio.studioId.eq(studioId),
                        user.userId.eq(userId)
                )
                .fetchOne();

        return StudioSettingResponse.builder()
                .studioTitle(tuple.get(studio.studioTitle))
                .studioCreateDate(tuple.get(studio.studioEndDate).minusDays(7))
                .studioEndDate(tuple.get(studio.studioEndDate))
                .nickname(tuple.get(user.nickName))
                .userPhotoUrl(tuple.get(user.picture))
                .build();

    }

}
