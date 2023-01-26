package offworkseekers.unnamed.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "comment_contents")
    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article articleId;

    @Builder
    public Comment(
            @NotNull Long commentId,
            String commentContents,
            User user,
            Article articleId
    ){
        this.commentId = commentId;
        this.commentContents = commentContents;
        this.user = user;
        this.articleId = articleId;
    }


}
