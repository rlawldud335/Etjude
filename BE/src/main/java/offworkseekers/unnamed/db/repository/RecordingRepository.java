package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Recording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordingRepository extends JpaRepository<Recording, Long>, RecordingRepositorySupport {

    @Modifying
    @Query(value = "delete from recording where studio_id in (:studio)", nativeQuery = true)
    void deleteByStudio(List<Long> studio);

    @Query(value = "select * from recording where studio_id in (:studio)", nativeQuery = true)
    List<Recording> findAllByStudio_StudioId(List<Long> studio);

}
