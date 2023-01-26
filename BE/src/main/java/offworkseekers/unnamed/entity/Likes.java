package offworkseekers.unnamed.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "likes")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes {

    @Id
    @GeneratedValue
    @Column(name = "likes_id")
    private Long likesId;

    @Column(name = "division")
    private int division;

    @Column(name = "video_id")
    private int videoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Likes(
            @NotNull Long likesId,
            int division,
            int videoId,
            User user
    ){
        this.likesId = likesId;
        this.division = division;
        this.videoId = videoId;
        this.user = user;
    }


}
