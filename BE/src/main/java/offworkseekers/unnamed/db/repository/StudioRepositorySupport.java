package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.api.response.StudioNavBarResponse;
import offworkseekers.unnamed.api.response.StudioRecordListResponse;
import offworkseekers.unnamed.api.response.StudioSettingResponse;
import offworkseekers.unnamed.db.entity.Film;

import java.util.List;

public interface StudioRepositorySupport {

    StudioNavBarResponse findStudioNavbar(Long studioId, String userId);

    StudioSettingResponse findStudioSetting(Long studioId, String userId);

    List<Film> getStudioFilmList(Long studioId);

    List<StudioRecordListResponse> findRecordingByStudioId(Long studioId);

}
