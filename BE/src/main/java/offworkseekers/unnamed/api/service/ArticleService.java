package offworkseekers.unnamed.api.service;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.request.ArticleCreateRequest;
import offworkseekers.unnamed.api.response.ArticleWithFilmUrlResponse;
import offworkseekers.unnamed.db.entity.Article;
import offworkseekers.unnamed.db.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Article createArticle(ArticleCreateRequest request) {
        Article article = request.toEntity();
        articleRepository.save(article);

        return article;
    }


    public List<ArticleWithFilmUrlResponse> getArticleList() {
        List<ArticleWithFilmUrlResponse> articles = articleRepository.getArticles();
        return articles;
    }




}
