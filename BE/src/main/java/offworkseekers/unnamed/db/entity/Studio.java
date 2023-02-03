package offworkseekers.unnamed.db.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "studio")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Studio {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "studio_id")
    private Long studioId;

    @Column(name = "studio_end_date")
    private LocalDate studioEndDate;

    @Column(name = "studio_repository")
    private String studioRepository;

    @Column(name = "studio_title")
    @Size(max = 20)
    private String studioTitle;

    @Column(name = "captain_id")
    private int captainId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @OneToMany(mappedBy = "studio")
    private List<TeamMember> teamMembers = new ArrayList<>();

    @OneToMany(mappedBy = "studio")
    private List<Film> films = new ArrayList<>();

    @Builder
    public Studio(Long studioId, LocalDate studioEndDate, String studioRepository, String studioTitle, int captainId, Story story, List<TeamMember> teamMembers, List<Film> films) {
        this.studioId = studioId;
        this.studioEndDate = studioEndDate;
        this.studioRepository = studioRepository;
        this.studioTitle = studioTitle;
        this.captainId = captainId;
        this.story = story;
        this.teamMembers = teamMembers;
        this.films = films;
    }
}
