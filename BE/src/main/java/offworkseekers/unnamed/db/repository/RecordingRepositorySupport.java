package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Recording;

public interface RecordingRepositorySupport {

    Recording findRecordingByStudioIdAndSceneId(Long studioId, Long sceneId);

}
