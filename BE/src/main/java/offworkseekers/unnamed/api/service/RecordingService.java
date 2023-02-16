package offworkseekers.unnamed.api.service;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offworkseekers.unnamed.config.S3Config;
import offworkseekers.unnamed.db.entity.Recording;
import offworkseekers.unnamed.db.repository.RecordingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RecordingService {

    private final RecordingRepository recordingRepository;
    private final S3Config s3Config;

    public void deleteRecordingsByDate(List<Long> expiredStudioId, AmazonS3 s3Client){

        String bucketName = s3Config.getBucketName();
        // recording list 받아와서 지움
        List<Recording> expiredRecordings = recordingRepository.findAllByStudio_StudioId(expiredStudioId);
        for (Recording recording : expiredRecordings) {
            String[] urls = recording.getRecordingVideoUrl().split("/");
            int len = urls.length;
            String fileName = urls[len-2] + "/" + urls[len-1];

            s3Client.deleteObject(bucketName, fileName);
        }

        // db에 있는 거 지움
        recordingRepository.deleteByStudio(expiredStudioId);
    }
}
