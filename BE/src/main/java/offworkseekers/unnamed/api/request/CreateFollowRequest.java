package offworkseekers.unnamed.api.request;

import lombok.Getter;

@Getter
public class CreateFollowRequest {

    String myUserId;
    String yourUserId;

}
