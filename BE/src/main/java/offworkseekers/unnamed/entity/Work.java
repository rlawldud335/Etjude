package offworkseekers.unnamed.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "work")
@NoArgsConstructor
public class Work {

    @Id @GeneratedValue
    @Column(name = "work_id")
    private int workId;

    @NonNull
    @Column(name = "work_title")
    private String workTitle;

    @NonNull
    @Column(name = "work_desc")
    private String workDesc;

    @NonNull
    @Column(name = "work_thumbnail_url")
    private String workThumbnailUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @Builder
    public Work(int workId, @NonNull String workTitle, @NonNull String desc, @NonNull String workThumbnailUrl, Category category, Story story) {
        this.workId = workId;
        this.workTitle = workTitle;
        this.workDesc = desc;
        this.workThumbnailUrl = workThumbnailUrl;
        this.category = category;
        this.story = story;
    }
}
