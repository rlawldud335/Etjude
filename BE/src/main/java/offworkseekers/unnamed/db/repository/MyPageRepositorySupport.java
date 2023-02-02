package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.api.response.*;

import java.util.List;

public interface MyPageRepositorySupport {

    List<MyPageStudiosResponse> getMyPageStudios(String userId);
    List<MyPageFilmsWithMembersResponse> getMyPageFilms(String userId);
    List<MyPageArticlesResponse> getMyPageArticles(String userId);
    MyPageLikesResponse getMyPageLikes(String userId);
    List<MyPageCommentsResponse> getMyPageComments(String userId);
    MyPageResponse getMyPage(String userId);

}
