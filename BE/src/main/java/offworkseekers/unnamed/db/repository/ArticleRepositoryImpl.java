package offworkseekers.unnamed.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offworkseekers.unnamed.api.response.*;
import offworkseekers.unnamed.db.entity.Comment;
import offworkseekers.unnamed.db.entity.Story;
import offworkseekers.unnamed.db.entity.Studio;
import offworkseekers.unnamed.db.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static offworkseekers.unnamed.db.entity.QArticle.article;
import static offworkseekers.unnamed.db.entity.QComment.comment;
import static offworkseekers.unnamed.db.entity.QFilm.film;
import static offworkseekers.unnamed.db.entity.QStory.story;
import static offworkseekers.unnamed.db.entity.QTeamMember.teamMember;
import static offworkseekers.unnamed.db.entity.QUser.user;

/**
 * 조회용 쿼리 구현부
 */
@RequiredArgsConstructor
@Slf4j
public class ArticleRepositoryImpl implements ArticleRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ArticleWithFilmUrlResponse> getArticles() {
        List<ArticleWithFilmUrlResponse> result = queryFactory
                .select(Projections.constructor(ArticleWithFilmUrlResponse.class,
                        article.articleId,
                        article.film.filmId,
                        article.articleThumbnailUrl,
                        article.articleTitle,
                        article.user.nickName,
                        article.user.picture,
                        article.articleCreatedDate,
                        article.articleViewCount))
                .from(article)
                .fetch();

        return result;
    }

    @Override
    public List<SearchFilmResponse> getSearchArticles(String keyword){
        List<SearchFilmResponse> result = queryFactory
                .select(Projections.constructor(SearchFilmResponse.class,
                        article.articleId,
                        article.articleTitle,
                        article.articleCreatedDate,
                        article.articleViewCount,
                        article.articleThumbnailUrl,
                        article.user.email,
                        article.user.nickName,
                        article.user.picture))
                .from(article)
                .where(article.articleTitle.contains(keyword))
                .fetch();
        return result;
    }

    @Override
    public FilmDetailResponse getFilmDetail(Long articleId){
        Story story = queryFactory
                .select(film.studio.story)
                .from(article)
                .where(article.articleId.eq(articleId))
                .fetchOne();
        Studio studio = queryFactory
                .select(article.film.studio)
                .from(article)
                .fetchOne();
        Long storyId = studio.getStory().getStoryId();

        List<Comment> comments = queryFactory
                .select(comment)
                .from(comment)
                .where(comment.article.articleId.eq(articleId))
                .fetch();

        List<User> users = queryFactory
                .select(user)
                .from(article)
                .where(article.articleId.eq(articleId))
                .fetch();
        FilmDetailResponse result = queryFactory
                .select(Projections.constructor(FilmDetailResponse.class,
                        article.articleTitle,
                        article.articleContent,
                        article.articleCreatedDate,
                        user.email,
                        user.nickName,
                        user.picture,
                        film.filmVideoUrl,
                        film.studio.studioTitle
                        ))
                .from(article)
                .where(article.articleId.eq(articleId))
                .fetchOne();

        result.addWorkTitle(searchWorkTitle(storyId));
        result.addStoryTitle(story.getStoryTitle());
        for (User user : users) {
            result.addTeamMember(
                    user.getEmail()
            );
        }

        for (Comment comment : comments) {
            result.addComment(
                    comment.builder()
                            .commentId(comment.getCommentId())
                            .commentContents(comment.getCommentContents())
                            .commentCreateTime(comment.getCommentCreateTime())
                            .build()
            );
        }
        return result;
    }
    @Override
    public List<PopularFilmResponse> getPopularArticles(){
        List<PopularFilmResponse> result = queryFactory
                .select(Projections.constructor(PopularFilmResponse.class,
                        article.articleId,
                        article.articleTitle,
                        article.articleViewCount,
                        article.film.filmId,
                        article.articleThumbnailUrl,
                        article.articleCreatedDate,
                        article.user.email,
                        article.user.picture))
                .from(article)
                .fetch();
        Collections.sort(result, (o1, o2) -> o2.getLikeCount() - o1.getLikeCount());
        return result;
    }

    @Override
    public List<MyFilmListResponse> getMyFilms(String userId){

        List<Tuple> fetch = queryFactory
                .select(
                        film.filmId,
                        film.filmTitle,
                        film.filmVideoUrl,
                        film.filmCreatedDate,
                        film.studio.studioId,
                        film.studio.story.storyId,
                        film.studio.story.storyTitle
                )
                .from(film)
                .where(film.user.userId.eq(userId))
                .fetch();

        List<MyFilmListResponse> myFilmListResponses = new ArrayList<>();

        for (Tuple tuple : fetch) {
            myFilmListResponses.add(
                    MyFilmListResponse.builder()
                            .filmId(tuple.get(film.filmId))
                            .filmCreatedDate(tuple.get(film.filmCreatedDate))
                            .filmVideoUrl(tuple.get(film.filmVideoUrl))
                            .storyTitle(tuple.get(film.studio.story.storyTitle))
                            .categoryName(
                                    searchCategoryName(tuple.get(film.studio.story.storyId))
                            )
                            .workTitle(
                                    searchWorkTitle(tuple.get(film.studio.story.storyId))
                            )
                            .teamMembers(
                                    searchTeamMembers(tuple.get(film.studio.studioId))
                            )
                            .build()
            );
        }

        return myFilmListResponses;

    }

    private List<String> searchTeamMembers(Long studioId) {
        return queryFactory
                .select(teamMember.user.userId)
                .from(teamMember)
                .where(
                        teamMember.studio.studioId.eq(studioId)
                )
                .fetch();
    }

    private String searchWorkTitle(Long storyId) {
        return queryFactory
                .select(story.work.workTitle)
                .from(story)
                .where(
                        story.storyId.eq(storyId)
                )
                .fetchOne();
    }

    private String searchCategoryName(Long storyId) {
        return queryFactory
                .select(story.category.categoryName)
                .from(story)
                .where(
                        story.storyId.eq(storyId)
                )
                .fetchOne();
    }
}
