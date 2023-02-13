package offworkseekers.unnamed.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offworkseekers.unnamed.db.entity.Recording;
import offworkseekers.unnamed.db.entity.Studio;

import java.util.List;

import static offworkseekers.unnamed.db.entity.QRecording.recording;

@RequiredArgsConstructor
@Slf4j
public class RecordingImpl implements RecordingSupport {

//    private final JPAQueryFactory queryFactory;
//
//    @Override
//    public List<Recording> getRecordListByStudio(List<Long> expiredStudios) {
//
//        log.debug("============================================");
//        List<Recording> recordListByStudio = queryFactory
//                .select(recording)
//                .from(recording)
//                .where(recording.studio.studioId.in(expiredStudios))
//                .fetch();
//
//        return recordListByStudio;
//    }

}
