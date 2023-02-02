package offworkseekers.unnamed.db.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long likesId;

    @Column(name = "division")
    private int division;

    @Column(name = "article_story_id")
    private int articleStoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Likes(
            Long likesId,
            int division,
            int articleStoryId,
            User user
    ){
        this.likesId = likesId;
        this.division = division;
        this.articleStoryId = articleStoryId;
        this.user = user;
    }


}
