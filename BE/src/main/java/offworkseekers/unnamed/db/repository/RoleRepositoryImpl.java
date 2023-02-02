package offworkseekers.unnamed.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offworkseekers.unnamed.api.dto.StoryLineDto;
import offworkseekers.unnamed.api.response.RoleWithLineOfSceneResponse;
import offworkseekers.unnamed.db.entity.Role;

import java.util.ArrayList;
import java.util.List;

import static offworkseekers.unnamed.db.entity.QLine.line;
import static offworkseekers.unnamed.db.entity.QRole.role;
import static offworkseekers.unnamed.db.entity.QScene.scene;

@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepositorySupport{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Role> getStoryRoleList(Long storyId) {
        List<Role> roleList = queryFactory
                .selectFrom(role)
                .where(role.story.storyId.eq(storyId))
                .fetch();
        return roleList;
    }

    @Override
    public List<RoleWithLineOfSceneResponse> getRoleWithLines(Long storyId) {
        List<Tuple> fetch = queryFactory
                .select(scene.sceneId, scene.sceneNumber, scene.role.roleId)
                .from(scene)
                .where(scene.story.storyId.eq(storyId))
                .orderBy(
                        scene.sceneNumber.asc()
                )
                .fetch();
        
        List<RoleWithLineOfSceneResponse> roleWithLineOfSceneResponse = new ArrayList<>();
        for (Tuple tuple : fetch) {
            Long roleId = tuple.get(scene.role.roleId);

            Tuple nowRole = queryFactory
                    .select(role.roleId, role.rolePhotoUrl, role.roleName)
                    .from(role)
                    .where(role.roleId.eq(roleId))
                    .fetchOne();

            roleWithLineOfSceneResponse.add(
                    RoleWithLineOfSceneResponse.builder()
                            .roleId(nowRole.get(role.roleId))
                            .rolePhotoUrl(nowRole.get(role.rolePhotoUrl))
                            .roleName(nowRole.get(role.roleName))
                            .sceneId(tuple.get(scene.sceneId))
                            .sceneNumber(tuple.get(scene.sceneNumber))
                            .lines(
                                    getFetch(tuple.get(scene.sceneId))
                            )
                            .build()
            );
        }

        return roleWithLineOfSceneResponse;
    }

    private List<StoryLineDto> getFetch(Long sceneId) {
        List<Tuple> fetch = queryFactory
                .select(line.lineScript, line.lineTimestamp)
                .from(line)
                .where(line.scene.sceneId.eq(sceneId))
                .orderBy(line.lineTimestamp.asc())
                .fetch();

        List<StoryLineDto> lines = new ArrayList<>();

        for (Tuple lineTuple : fetch) {
            lines.add(
                    StoryLineDto.builder()
                            .lineTimeStamp(lineTuple.get(line.lineTimestamp))
                            .line(lineTuple.get(line.lineScript))
                            .build()
            );
        }
        return lines;
    }
}
