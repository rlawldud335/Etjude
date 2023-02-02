package offworkseekers.unnamed.api.service;

import lombok.RequiredArgsConstructor;
import offworkseekers.unnamed.db.entity.Comment;
import offworkseekers.unnamed.db.repository.ArticleRepository;
import offworkseekers.unnamed.db.repository.CommentRepository;
import offworkseekers.unnamed.db.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public Comment createComment(String userId, Long articleId, String commentContents){

        Comment comment = Comment.builder()
                .commentContents(commentContents)
                .commentCreateTime(LocalDateTime.now())
                .user(userRepository.findById(userId).get())
                .article(articleRepository.findById(articleId).get())
                .build();

        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }

    public void updateComment(Long commentId, String commentContents){
        commentRepository.updateComment(commentId, commentContents);
    }

}
