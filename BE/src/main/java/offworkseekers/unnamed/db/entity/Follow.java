package offworkseekers.unnamed.db.entity;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "follow")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "followerId")
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "followingId")
    private User following;

    @Builder
    public Follow(
            @NotNull Long followId,
            User follower,
            User following
    ){
        this.followId = followId;
        this.follower = follower;
        this.following = following;
    }

}