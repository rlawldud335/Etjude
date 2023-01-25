package offworkseekers.unnamed.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "like")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @GeneratedValue
    @Column(name = "division")
    private Long likeId;

    @Column(name = "division")
    private int division;

    @Column(name = "video_id")
    private int videoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Builder
    public Like(
            @NotNull Long likeId,
            int division,
            int videoId,
            User userId
    ){
        this.userId = userId;
        this.division = division;
        this.videoId = videoId;
        this.userId = userId;
    }


}
