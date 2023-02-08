package offworkseekers.unnamed.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offworkseekers.unnamed.api.request.ArticleCreateRequest;
import offworkseekers.unnamed.api.request.ArticleEditRequest;
import offworkseekers.unnamed.api.response.*;
import offworkseekers.unnamed.db.entity.Article;
import offworkseekers.unnamed.db.entity.Film;
import offworkseekers.unnamed.db.entity.Likes;
import offworkseekers.unnamed.db.entity.User;
import offworkseekers.unnamed.db.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleRepositoryImpl articleRepositoryImpl;
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    private final StudioRepository studioRepository;
    @Transactional
    public void createArticle(ArticleCreateRequest request) {
        String userId = request.getUserId();
        Long filmId = request.getFilmId();
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        Film film = filmRepository.findById(filmId).orElse(null);
        Article article = Article.builder()
                        .articleContent(request.getArticleContent())
                        .articleCreatedDate(LocalDate.now())
                        .articleThumbnailUrl(request.getArticleThumbnailUrl())
                        .articleTitle(request.getArticleTitle())
                        .film(film)
                        .user(user)
                        .build();
        articleRepository.save(article);
    }


    public List<ArticleWithFilmUrlResponse> getArticleList() {
        List<ArticleWithFilmUrlResponse> articles = articleRepository.getArticles();
        return articles;
    }

    public List<SearchFilmResponse> getSearchArticleList(String keyword){
        List<SearchFilmResponse> articles = articleRepository.getSearchArticles(keyword);
        return articles;
    }

    public FilmDetailResponse getFilmDetail(Long articleId){
        FilmDetailResponse filmDetail = articleRepository.getFilmDetail(articleId);
        return filmDetail;
    }

    public List<PopularFilmResponse> getPopularArticleList(){
        List<PopularFilmResponse> articles = articleRepository.getPopularArticles();
        return articles;

    }

    public List<MyFilmListResponse> getModalFilmList(String userId) {
        return articleRepository.getMyFilms(userId);
    }


    public Optional<Likes> getArticleLikeStatus(int articleId, int division, String userId){
        return likesRepository.findArticleLikeConnection(articleId, division, userId);
    }

    @Transactional
    public void editArticleLike(int articleId, int division, String userId) {
        Optional<Likes> likes = getArticleLikeStatus(articleId, division, userId);
        if (likes.isEmpty()) {
            User user = userRepository.findById(userId).orElse(null);
            Likes build = Likes.builder()
                    .articleStoryId(articleId)
                    .division(division)
                    .user(user)
                    .build();
            likesRepository.save(build);
        }

    }
    @Transactional
    public void deleteArticleLike(int storyId, int division, String userId) {
        Optional<Likes> likes = getArticleLikeStatus(storyId, division, userId);
        likesRepository.deleteById(likes.get().getLikesId());
    }

    @Transactional
    public void deleteArticle(Long articleId, String userId) {
        Article article = articleRepository.findById(articleId).orElse(null);
        String articleWriterId = article.getUser().getUserId();
        if(articleWriterId.equals(userId)){
            articleRepository.deleteById(articleId);
        }
    }

    @Transactional
    public void editArticle(ArticleEditRequest request) {
        String userId = request.getUserId();
        Article article = articleRepository.findById(request.getArticleId()).orElse(null);
        String articleWriterId = article.getUser().getUserId();
        if(articleWriterId.equals(userId)){
            article.setArticleTitle(request.getArticleTitle());
            article.setArticleContent(request.getArticleContent());
            articleRepository.save(article);
        }

    }

}
