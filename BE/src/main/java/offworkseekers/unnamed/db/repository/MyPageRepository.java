package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.db.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageRepository extends JpaRepository<Article, Long>, MyPageRepositorySupport{
}
