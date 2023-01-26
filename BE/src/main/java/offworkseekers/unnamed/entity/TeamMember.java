package offworkseekers.unnamed.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "team_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamMember {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_member_id")
    private Long teamMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public TeamMember(Long teamMemberId, Team team, User user) {
        this.teamMemberId = teamMemberId;
        this.team = team;
        this.user = user;
    }
}
