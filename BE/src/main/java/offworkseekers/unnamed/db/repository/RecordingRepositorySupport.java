package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Recording;

public interface RecordingRepositorySupport {

    Recording findRecordingByStudioIdAndSceneId(int studioId, int sceneId);

}
