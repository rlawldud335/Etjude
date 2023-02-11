package offworkseekers.unnamed.api.service;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offworkseekers.unnamed.db.entity.Film;
import offworkseekers.unnamed.db.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FilmService {

    private final FilmRepository filmRepository;

    public void deleteFilmsByDate(AmazonS3 s3Client){
        List<Film> filmList = filmRepository.findAll();
        LocalDate nowMinus7 = LocalDate.now().minusDays(7);
        for (Film film : filmList) {
            if(film.getFilmCreatedDate() != null && film.getFilmCreatedDate().isBefore(nowMinus7)){
                filmRepository.delete(film);

                String bucketName = "s3ffmpegtest";
                String[] urls = film.getFilmVideoUrl().split("/");
                int len = urls.length;
                String fileName = urls[len-2] + "/" + urls[len-1];

                s3Client.deleteObject(bucketName, fileName);
            }
        }
    }
}