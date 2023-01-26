package offworkseekers.unnamed.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "team")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_end_date")
    private LocalDate teamEndDate;

    @Column(name = "team_repository")
    private String teamRepository;

    @Column(name = "team_title")
    @Size(max = 20)
    private String teamTitle;

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
    public Team(Long teamId, LocalDate teamEndDate, String teamRepository, String teamTitle, int captainId, Story story, List<TeamMember> teamMembers, List<Film> films) {
        this.teamId = teamId;
        this.teamEndDate = teamEndDate;
        this.teamRepository = teamRepository;
        this.teamTitle = teamTitle;
        this.captainId = captainId;
        this.story = story;
        this.teamMembers = teamMembers;
        this.films = films;
    }
}
