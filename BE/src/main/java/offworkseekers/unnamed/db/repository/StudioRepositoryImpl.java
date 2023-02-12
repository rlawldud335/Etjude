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

import java.util.ArrayList;
import java.util.List;

import static offworkseekers.unnamed.db.entity.QFilm.film;
import static offworkseekers.unnamed.db.entity.QRecording.recording;
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
                        studio.story.storyVideoUrl
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
    public List<StudioRecordListResponse> findRecordingByStudioId(Long studioId) {
        List<Tuple> fetch = queryFactory
                .select(recording.recordingVideoUrl, recording.scene.sceneId, recording.scene.sceneNumber, recording.userId)
                .from(recording)
                .where(recording.studio.studioId.eq(studioId))
                .fetch();
        List<StudioRecordListResponse> studioRecordListResponseList = new ArrayList<>();
        for (Tuple tuple : fetch) {
            String profileURL = queryFactory
                    .select(user.picture)
                    .from(user)
                    .where(user.userId.eq(tuple.get(recording.userId)))
                    .fetchOne();

            studioRecordListResponseList.add(
                    StudioRecordListResponse.builder()
                            .recordVideoUrl(tuple.get(recording.recordingVideoUrl))
                            .sceneId(tuple.get(recording.scene.sceneId))
                            .sceneNumber(tuple.get(recording.scene.sceneNumber))
                            .userId(tuple.get(recording.userId))
                            .profileURL(profileURL)
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
