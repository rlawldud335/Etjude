package offworkseekers.unnamed.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.response.StoryDetailResponse;
import offworkseekers.unnamed.api.response.StoryListResponse;
import offworkseekers.unnamed.api.response.StoryListWithTotalCountResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static offworkseekers.unnamed.db.entity.QCategory.category;
import static offworkseekers.unnamed.db.entity.QLikes.likes;
import static offworkseekers.unnamed.db.entity.QStory.story;
import static offworkseekers.unnamed.db.entity.QStudio.studio;
import static offworkseekers.unnamed.db.entity.QTeamMember.teamMember;
import static offworkseekers.unnamed.db.entity.QWork.work;

@RequiredArgsConstructor
public class StoryRepositoryImpl implements StoryRepositorySupport{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StoryListResponse> getStoryListRecommendedByLike(int pageNum) {
        List<Tuple> fetch = queryFactory
                .select(story.storyId, story.storyThumbnailUrl, story.storyTitle, category.categoryName, work.workTitle)
                .from(story, category, work)
                .where(story.category.categoryId.eq(category.categoryId),
                        story.work.workId.eq(work.workId))
                .fetch();

        List<StoryListResponse> storyListRecommendedByLikeResponse = new ArrayList<>();

        for (Tuple tuple : fetch) {
            storyListRecommendedByLikeResponse.add(StoryListResponse.builder()
                    .storyId(tuple.get(story.storyId))
                    .storyTitle(tuple.get(story.storyTitle))
                    .storyThumbnailUrl(tuple.get(story.storyThumbnailUrl))
                    .likeCount(
                            getStoryLikeCount(tuple.get(story.storyId))
                    )
                    .categoryName(tuple.get(category.categoryName))
                    .workTitle(tuple.get(work.workTitle))
                    .build()
            );
        }
        Collections.sort(storyListRecommendedByLikeResponse, (o1, o2) -> o2.getLikeCount() - o1.getLikeCount());

        int totalNum = storyListRecommendedByLikeResponse.size();
        int startIdx = 12 * (pageNum - 1);
        int endIdx = startIdx + 12;
        if(totalNum - startIdx < 12){
            return storyListRecommendedByLikeResponse.subList(startIdx, totalNum);
        }
        return storyListRecommendedByLikeResponse.subList(startIdx, endIdx);
    }

    private int getStoryLikeCount(Long storyId) {

        Long result = queryFactory.select(likes.count())
                .from(likes)
                .where(likes.division.eq(1),
                        likes.articleStoryId.eq(Math.toIntExact(storyId)))
                .fetchOne();

        return Math.toIntExact(result);
    }

    @Override
    public StoryDetailResponse getStoryDetail(Long storyId, String userId) {
        Tuple tuple = queryFactory
                .select(story.storyVideoUrl, category.categoryName, work.workTitle, story.storyTitle, story.storySummary)
                .from(story, work, category)
                .where(story.storyId.eq(storyId),
                        story.category.categoryId.eq(category.categoryId),
                        story.work.workId.eq(work.workId)
                )
                .fetchOne();

        Long studioStack = queryFactory
                .select(studio.count())
                .from(studio)
                .where(studio.story.storyId.eq(storyId))
                .fetchOne();


        List<Long> fetch = queryFactory
                .select(teamMember.studio.studioId)
                .from(teamMember)
                .where(teamMember.user.userId.eq(userId))
                .fetch();

        Long studioIdResponse = 0L;
        for (Long fetchedStudioId : fetch) {
            studioIdResponse = queryFactory
                    .select(studio.studioId)
                    .from(studio)
                    .where(studio.studioId.eq(fetchedStudioId))
                    .fetchOne();
        }

        StoryDetailResponse storyDetail = StoryDetailResponse.builder()
                .storyLikeCount(
                        getStoryLikeCount(storyId)
                )
                .studioStack(Math.toIntExact(studioStack))
                .storySummary(tuple.get(story.storySummary))
                .storyVideoUrl(tuple.get(story.storyVideoUrl))
                .storyTitle(tuple.get(story.storyTitle))
                .categoryName(tuple.get(category.categoryName))
                .workTitle(tuple.get(work.workTitle))
                .studioId(studioIdResponse)
                .build();

        return storyDetail;
    }

    @Override
    public StoryListWithTotalCountResponse getStorySearchList(String keyword, Long categoryId, int pageNum) {
        List<Tuple> fetch = new ArrayList<>();
        if (categoryId == 0L){
            fetch = queryFactory
                    .select(
                            story.storyId,
                            story.storyThumbnailUrl,
                            story.category.categoryName,
                            story.storyTitle,
                            story.work.workTitle)
                    .from(story)
                    .where(story.storyTitle.contains(keyword))
                    .fetch();
        } else {
            fetch = queryFactory
                    .select(
                            story.storyId,
                            story.storyThumbnailUrl,
                            story.category.categoryName,
                            story.storyTitle,
                            story.work.workTitle)
                    .from(story)
                    .where(
                            story.category.categoryId.eq(categoryId),
                            story.storyTitle.contains(keyword)
                    )
                    .fetch();
        }

        List<StoryListResponse> storySearchResult = new ArrayList<>();

        for (Tuple tuple : fetch) {
            storySearchResult.add(
                    StoryListResponse.builder()
                    .storyId(tuple.get(story.storyId))
                    .storyTitle(tuple.get(story.storyTitle))
                    .storyThumbnailUrl(tuple.get(story.storyThumbnailUrl))
                    .likeCount(
                            getStoryLikeCount(tuple.get(story.storyId))
                    )
                    .categoryName(tuple.get(story.category.categoryName))
                    .workTitle(tuple.get(story.work.workTitle))
                    .build()
            );
        }
        int totalNum = storySearchResult.size();
        int startIdx = 12 * (pageNum - 1);
        int endIdx = startIdx + 12;

        StoryListWithTotalCountResponse.builder()
                .totalCount(totalNum)
                .storyListResponses(storySearchResult.subList(startIdx, totalNum))
                .build();


        if(totalNum - startIdx < 12){
            return StoryListWithTotalCountResponse.builder()
                    .totalCount(totalNum)
                    .storyListResponses(storySearchResult.subList(startIdx, totalNum))
                    .build();
        }

        return StoryListWithTotalCountResponse.builder()
                .totalCount(totalNum)
                .storyListResponses(storySearchResult.subList(startIdx, endIdx))
                .build();
    }
}
