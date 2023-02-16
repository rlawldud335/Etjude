package offworkseekers.unnamed.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.db.entity.Recording;

import static offworkseekers.unnamed.db.entity.QRecording.recording;

@RequiredArgsConstructor
public class RecordingRepositoryImpl implements RecordingRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Override
    public Recording findRecordingByStudioIdAndSceneId(int studioId, int sceneId) {
        return queryFactory
                .selectFrom(recording)
                .where(
                        recording.studio.studioId.eq(Long.valueOf(studioId)),
                        recording.scene.sceneId.eq(Long.valueOf(sceneId))
                )
                .fetchOne();
    }
}
