package offworkseekers.unnamed.db.entity;

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
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public TeamMember(Long teamMemberId, Studio studio, User user) {
        this.teamMemberId = teamMemberId;
        this.studio = studio;
        this.user = user;
    }
}
