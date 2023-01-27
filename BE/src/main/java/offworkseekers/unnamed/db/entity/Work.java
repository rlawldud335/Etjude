package offworkseekers.unnamed.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "work")
@NoArgsConstructor
public class Work {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_id")
    private Long workId;

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

    @OneToMany(mappedBy = "work")
    private List<Story> stories = new ArrayList<>();

    @Builder
    public Work(Long workId, @NonNull String workTitle, @NonNull String desc, @NonNull String workThumbnailUrl, Category category, List<Story> stories) {
        this.workId = workId;
        this.workTitle = workTitle;
        this.workDesc = desc;
        this.workThumbnailUrl = workThumbnailUrl;
        this.category = category;
        this.stories = stories;
    }
}
