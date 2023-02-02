package offworkseekers.unnamed.db.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "article_content")
    private String articleContent;

    @Column(name = "article_title", length = 25)
    private String articleTitle;

    @Column(name = "article_thumbnail_url")
    private String articleThumbnailUrl;

    @Column(name = "article_view_count")
    private int articleViewCount;

    @Column(name = "article_created_date")
    private LocalDate articleCreatedDate;

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
            LocalDate articleCreatedDate,
            int articleViewCount,
            Film film,
            User user
    ){
        this.articleId = articleId;
        this.articleContent = articleContent;
        this.articleTitle = articleTitle;
        this.articleThumbnailUrl = articleThumbnailUrl;
        this.articleCreatedDate = articleCreatedDate;
        this.articleViewCount = articleViewCount;
        this.film = film;
        this.user = user;
    }
}
