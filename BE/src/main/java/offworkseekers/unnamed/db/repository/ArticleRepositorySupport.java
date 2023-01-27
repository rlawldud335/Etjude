package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.api.response.ArticleWithFilmUrlResponse;
import offworkseekers.unnamed.db.entity.Article;

import java.util.List;
import java.util.Optional;

/**
 * Article 조회용 쿼리 인터페이스
 */
public interface ArticleRepositorySupport {

    List<ArticleWithFilmUrlResponse> getArticles();



}
