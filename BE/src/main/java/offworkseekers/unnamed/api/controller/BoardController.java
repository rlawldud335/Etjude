package offworkseekers.unnamed.api.controller;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.request.ArticleCreateRequest;
import offworkseekers.unnamed.api.request.ArticleEditRequest;
import offworkseekers.unnamed.api.response.*;
import offworkseekers.unnamed.api.service.ArticleService;
import offworkseekers.unnamed.db.entity.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final ArticleService articleService;

    @PutMapping(value = "/api/v1/board/create")
    public void saveArticle(@RequestBody @Valid ArticleCreateRequest request) {
        articleService.createArticle(request);
    }

    @GetMapping(value = "/api/v1/board")
    public List<ArticleWithFilmUrlResponse> getArticleList(@RequestParam(value = "pageNum") int pageNum) {
        List<ArticleWithFilmUrlResponse> articleList = articleService.getArticleList(pageNum);
        return articleList;
    }

    @GetMapping(path = "/api/v1/board/search/{keyword}")
    public List<SearchFilmResponse> getSearchArticleList(@PathVariable String keyword){
        List<SearchFilmResponse> articleList = articleService.getSearchArticleList(keyword);
        return articleList;

    }

    @GetMapping(path = "/api/v1/board/detail/{articleId}")
    public FilmDetailResponse getFilmDetail(@PathVariable Long articleId){
        FilmDetailResponse filmDetail = articleService.getFilmDetail(articleId);
        return filmDetail;
    }

    @GetMapping(value = "/api/v1/board/popular")
    public List<PopularFilmResponse> getPopularArticleList() {
        List<PopularFilmResponse> articleList = articleService.getPopularArticleList();
        return articleList;
    }

    @PostMapping(value = "api/v1/board/modal")
    public List<MyFilmListResponse> getModalFilmList(@RequestBody @Valid Map<String, String> param){
        String userId = param.get("user_id");
        return articleService.getModalFilmList(userId);
    }


    @PostMapping(value = "/api/v1/board/like")
    public boolean storyLikeStatus(@RequestBody @Valid Map<String, Object> param) {
        int articleId = (int) param.get("article_id");
        int division = (int) param.get("division");
        String userId = (String) param.get("user_id");

        if (articleService.getArticleLikeStatus(articleId, division, userId).isEmpty()) {
            return false;
        }

        return true;
    }

    @PutMapping(value = "/api/v1/board/like")
    public ResponseEntity storyLike(@RequestBody @Valid Map<String, Object> param) {
        int articleId = (int) param.get("article_id");
        int division = (int) param.get("division");
        String userId = (String) param.get("user_id");

        if (userId == null || userId.replaceAll(" ", "").equals("")) {
            return ResponseEntity.badRequest().build();
        }
        articleService.editArticleLike(articleId, division, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/api/v1/board/like")
    public void storyLikeCancel(@RequestBody @Valid Map<String, Object> param) {
        int articleId = (int) param.get("article_id");
        int division = (int) param.get("division");
        String userId = (String) param.get("user_id");

        articleService.deleteArticleLike(articleId, division, userId);
    }

    @DeleteMapping(value = "/api/v1/board/delete")
    public void articleDelete(@RequestBody @Valid Map<String, Object> request) {
        Long articleId = Long.parseLong(String.valueOf(request.get("article_id")));
        String userId = (String) request.get("user_id");
        articleService.deleteArticle(articleId, userId);
    }

    @PatchMapping(value = "/api/v1/board/edit")
    public void articleEdit(@RequestBody @Valid ArticleEditRequest request){
        articleService.editArticle(request);
    }

}
