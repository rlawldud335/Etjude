package offworkseekers.unnamed.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "article_content")
    private String articleContent;

    @Column(name = "article_title", length = 25)
    private String articleTitle;

    @Column(name = "article_tumbnail_url")
    private String articleThumbnailUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Article(
            @NotNull Long articleId,
            String articleContent,
            String articleTitle,
            String articleThumbnailUrl,
            User user
    ){
        this.articleId = articleId;
        this.articleContent = articleContent;
        this.articleTitle = articleTitle;
        this.articleThumbnailUrl = articleThumbnailUrl;
        this.user = user;
    }
}
