package offworkseekers.unnamed.api.controller;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.request.ArticleCreateRequest;
import offworkseekers.unnamed.api.response.ArticleWithFilmUrlResponse;
import offworkseekers.unnamed.api.response.CreateArticleResponse;
import offworkseekers.unnamed.api.service.ArticleService;
import offworkseekers.unnamed.db.entity.Article;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final ArticleService articleService;

    @PutMapping(value = "/api/v1/board/create")
    public CreateArticleResponse saveArticle(@RequestBody @Valid ArticleCreateRequest request) {
        Article article = articleService.createArticle(request);
        return new CreateArticleResponse(article.getArticleId(), article.getArticleContent(), article.getArticleTitle(), article.getArticleThumbnailUrl());
    }

    @GetMapping(value = "/api/v1/board")
    public List<ArticleWithFilmUrlResponse> getArticleList() {
        List<ArticleWithFilmUrlResponse> articleList = articleService.getArticleList();
        return articleList;

    }



}
