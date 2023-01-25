package offworkseekers.unnamed.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "story")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Film {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id")
    private Long id;

    @Column(name = "film_title")
    @Size(max = 30)
    private String title;

    @Column(name = "film_video_url")
    private String filmUrl;

    @Column(name = "film_created_date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "film")
    private List<Article> articles = new ArrayList<>();

    @Builder
    public Film(Long id, String title, String filmUrl, LocalDate date, Team team, List<Article> articles) {
        this.id = id;
        this.title = title;
        this.filmUrl = filmUrl;
        this.date = date;
        this.team = team;
    }

    public void addArticle(Article article) {
        articles.add(article);
    }


}
