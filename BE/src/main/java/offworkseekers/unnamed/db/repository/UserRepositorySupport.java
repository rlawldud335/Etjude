package offworkseekers.unnamed.db.repository;

import offworkseekers.unnamed.api.response.UserSearchResponse;

import java.util.List;

public interface UserRepositorySupport {

    List<UserSearchResponse> findUserSimple(String keyword);

}
