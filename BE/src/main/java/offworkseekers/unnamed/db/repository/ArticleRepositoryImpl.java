package offworkseekers.unnamed.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.db.entity.Article;

import java.util.List;

/**
 * 조회용 쿼리 구현부
 */
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Article> getAllList() {
        return null;
    }

    @Override
    public Article selectOne() {
        return null;
    }
}
