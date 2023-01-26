package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Article;

import java.util.List;

/**
 * Article 조회용 쿼리 인터페이스
 */
public interface ArticleRepositorySupport {

    List<Article> getAllList();

    Article selectOne();

    // 메뉴 모음집
    // 탕수육 짜장면

}
