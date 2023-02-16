package offworkseekers.unnamed.db.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "team_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamMember {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_member_id", columnDefinition = "INT UNSIGNED")
    private Long teamMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public TeamMember(Studio studio, User user) {
        this.studio = studio;
        this.user = user;
    }
}
