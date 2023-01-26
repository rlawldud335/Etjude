package offworkseekers.unnamed.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

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
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "film_title")
    @Size(max = 30)
    private String filmTitle;

    @Column(name = "film_video_url")
    private String filmVideoUrl;

    @Column(name = "film_created_date")
    private LocalDate filmCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "film")
    private List<Article> articles = new ArrayList<>();

    @Builder
    public Film(Long filmId, String filmTitle, String filmVideoUrl, LocalDate filmCreatedDate, Team team, List<Article> articles) {
        this.filmId = filmId;
        this.filmTitle = filmTitle;
        this.filmVideoUrl = filmVideoUrl;
        this.filmCreatedDate = filmCreatedDate;
        this.team = team;
    }

    public void addArticle(Article article) {
        articles.add(article);
    }


}
