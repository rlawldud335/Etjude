package offworkseekers.unnamed.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "story")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private int id;

    @Column(name = "team_end_date")
    private LocalDateTime endDate;

    @Column(name = "team_repository")
    private String repository;

    @Column(name = "team_title")
    @Size(max = 20)
    private String title;

    @Column(name = "captain_id")
    private int captainId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @OneToMany(mappedBy = "team")
    private List<TeamMember> teamMembers = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Film> films = new ArrayList<>();

    @Builder
    public Team(int id, LocalDateTime endDate, String repository, String title, int captainId, Story story, List<TeamMember> teamMembers, List<Film> films) {
        this.id = id;
        this.endDate = endDate;
        this.repository = repository;
        this.title = title;
        this.captainId = captainId;
        this.story = story;
        this.teamMembers = teamMembers;
        this.films = films;
    }
}
