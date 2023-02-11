package offworkseekers.unnamed.common;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.service.FilmService;
import offworkseekers.unnamed.api.service.RecordingService;
import offworkseekers.unnamed.api.service.StudioService;
import offworkseekers.unnamed.config.S3Config;
import offworkseekers.unnamed.db.entity.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final FilmService filmService;
    private final StudioService studioService;
    private final RecordingService recordingService;
    private final S3Config s3Config;


    @Scheduled(cron = "00 00 00 * * *", zone = "Asia/Seoul")
    public void FilmRemove(){

        Regions clientRegion = Regions.AP_NORTHEAST_2;
        String bucketName = "s3ffmpegtest";

        BasicAWSCredentials credentials = new BasicAWSCredentials(s3Config.getAccessKey(), s3Config.getSecretKey());
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        filmService.deleteFilmsByDate(s3Client);
        List<Long> expiredStudioIds = studioService.getExpiredStudioIds();
        recordingService.deleteRecordingsByDate(expiredStudioIds, s3Client);

    }

}
