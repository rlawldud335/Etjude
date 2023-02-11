package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.api.response.*;
import offworkseekers.unnamed.db.entity.Article;

import java.util.List;
import java.util.Optional;

/**
 * Article 조회용 쿼리 인터페이스
 */
public interface ArticleRepositorySupport {

    List<ArticleWithFilmUrlResponse> getArticles(int pageNum);
    List<SearchFilmResponse> getSearchArticles(String keyword, int pageNum);
    FilmDetailResponse getFilmDetail(Long articleId);
    List<PopularFilmResponse> getPopularArticles();
    List<MyFilmListResponse> getMyFilms(String userId);
}
