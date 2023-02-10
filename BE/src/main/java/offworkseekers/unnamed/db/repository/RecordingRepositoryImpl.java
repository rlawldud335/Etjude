package offworkseekers.unnamed.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.db.entity.QRecording;
import offworkseekers.unnamed.db.entity.Recording;

@RequiredArgsConstructor
public class RecordingRepositoryImpl implements RecordingRepositorySupport {

    private final JPAQueryFactory queryFactory;


    @Override
    public Recording findRecordingByStudioIdAndSceneId(Long studioId, Long sceneId) {
        return queryFactory
                .selectFrom(QRecording.recording)
                .where(
                        QRecording.recording.studio.studioId.eq(studioId),
                        QRecording.recording.scene.sceneId.eq(sceneId)
                )
                .fetchOne();
    }
}
