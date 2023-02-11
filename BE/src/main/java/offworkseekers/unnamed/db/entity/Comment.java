package offworkseekers.unnamed.db.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", columnDefinition = "INT UNSIGNED")
    private Long commentId;

    @Column(name = "comment_contents")
    private String commentContents;

    @Column(name = "comment_create_time")
    private LocalDateTime commentCreateTime;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    public Comment(
            @NotNull Long commentId,
            String commentContents,
            LocalDateTime commentCreateTime,
            User user,
            Article article
    ){
        this.commentId = commentId;
        this.commentContents = commentContents;
        this.commentCreateTime = commentCreateTime;
        this.user = user;
        this.article = article;
    }


}
