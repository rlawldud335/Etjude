package offworkseekers.unnamed.api.request;

import lombok.Getter;

@Getter
public class UpdateCommentRequest {

    Long commentId;
    String commentContents;
}
