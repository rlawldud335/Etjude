package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositorySupport {

}
