package offworkseekers.unnamed.db.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "film")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Film {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "INT UNSIGNED")
    private Long filmId;

    @Column(name = "film_title")
    @Size(max = 30)
    private String filmTitle;

    @Column(name = "film_video_url")
    private String filmVideoUrl;

    @Column(name = "film_created_date")
    private LocalDate filmCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "film")
    private List<Article> articles = new ArrayList<>();

    @Builder
    public Film(Long filmId, String filmTitle, String filmVideoUrl, LocalDate filmCreatedDate, Studio studio, User user) {
        this.filmId = filmId;
        this.filmTitle = filmTitle;
        this.filmVideoUrl = filmVideoUrl;
        this.filmCreatedDate = filmCreatedDate;
        this.studio = studio;
        this.user = user;
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void setFilmCreatedDate(LocalDate filmCreatedDate){ this.filmCreatedDate = filmCreatedDate; }

}
