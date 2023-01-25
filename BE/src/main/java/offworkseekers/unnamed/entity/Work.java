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
    private int id;

    @NonNull
    @Column(name = "work_title")
    private String title;

    @NonNull
    @Column(name = "work_desc")
    private String desc;

    @NonNull
    @Column(name = "work_thumbnail_url")
    private String thumbnailUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @Builder
    public Work(int id, @NonNull String title, @NonNull String desc, @NonNull String thumbnailUrl, Category category) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.thumbnailUrl = thumbnailUrl;
        this.category = category;
    }
}
