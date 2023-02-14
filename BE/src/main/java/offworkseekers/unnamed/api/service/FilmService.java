package offworkseekers.unnamed.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offworkseekers.unnamed.db.entity.*;
import offworkseekers.unnamed.db.repository.ArticleRepositoryImpl;
import offworkseekers.unnamed.db.repository.FilmRepository;
import offworkseekers.unnamed.db.repository.StudioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static offworkseekers.unnamed.db.entity.QTeamMember.teamMember;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FilmService {

    private final FilmRepository filmRepository;
    private final StudioRepository studioRepository;

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

    public void createFilm(int studioId, String filmVideoUrl) {
        Studio studio = studioRepository.findById(Long.valueOf(studioId)).orElse(null);

        Film film = Film.builder()
                    .filmCreatedDate(LocalDate.now())
                    .filmTitle("")
                    .filmVideoUrl(filmVideoUrl)
                    .studio(studio)
                    .user(null)
                    .build();

            filmRepository.save(film);

    }
}