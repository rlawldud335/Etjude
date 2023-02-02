package offworkseekers.unnamed.api.request;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

    String userId;
    Long articleId;
    String commentContents;

}
