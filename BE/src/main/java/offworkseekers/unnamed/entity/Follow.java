package offworkseekers.unnamed.entity;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "follow")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User followerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User followingId;

    @Builder
    public Follow(
            @NotNull Long likeId,
            User followerId,
            User followingId
    ){
        this.likeId = likeId;
        this.followerId = followerId;
        this.followingId = followingId;
    }

}
