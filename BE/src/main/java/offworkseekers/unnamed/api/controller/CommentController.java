package offworkseekers.unnamed.api.controller;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.api.request.CreateCommentRequest;
import offworkseekers.unnamed.api.request.UpdateCommentRequest;
import offworkseekers.unnamed.api.service.CommentService;
import offworkseekers.unnamed.db.entity.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/v1/comment")
    public Comment createComment(@RequestBody CreateCommentRequest request){

        Comment comment = commentService.createComment(request.getUserId(), request.getArticleId(), request.getCommentContents());
        return comment;
    }

    @DeleteMapping("/api/v1/comment")
    public void deleteComment(@RequestBody Map<String, Long> request){
        commentService.deleteComment(request.get("comment_id"));
    }

    @PatchMapping("/api/v1/comment")
    public void updateComment(@RequestBody UpdateCommentRequest request){
        commentService.updateComment(request.getCommentId(), request.getCommentContents());
    }

}
