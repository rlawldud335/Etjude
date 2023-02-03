package offworkseekers.unnamed.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.response.ArticleWithFilmUrlResponse;
import offworkseekers.unnamed.db.entity.Article;
import offworkseekers.unnamed.db.entity.QArticle;
import offworkseekers.unnamed.db.entity.QFilm;

import java.util.List;
import java.util.Optional;

import static offworkseekers.unnamed.db.entity.QArticle.article;
import static offworkseekers.unnamed.db.entity.QFilm.film;

/**
 * 조회용 쿼리 구현부
 */
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ArticleWithFilmUrlResponse> getArticles() {
        List<ArticleWithFilmUrlResponse> result = queryFactory
                .select(Projections.constructor(ArticleWithFilmUrlResponse.class,
                        article.articleId,
                        film.filmId,
                        article.articleThumbnailUrl,
                        article.articleTitle,
                        article.user.nickName,
                        article.user.picture,
                        article.articleCreatedDate,
                        article.articleViewCount))
                .from(article, film)
                .fetch();

        return result;
    }
}
