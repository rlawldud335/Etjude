package offworkseekers.unnamed.db.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "story")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Story {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "story_id")
    private Long storyId;

    @Column(name = "story_title")
    @Size(max = 15)
    private String storyTitle;

    @Column(name = "story_video_url")
    private String storyVideoUrl;

    @Column(name = "story_desc")
    private String storyDesc;

    @Column(name = "story_summary")
    private String storySummary;

    @Column(name = "story_thumbnail_url")
    private String storyThumbnailUrl;

//    @Column(name = "story_like")
//    private int storyLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Work work;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "story")
    private List<Scene> scenes = new ArrayList<>();

    @OneToMany(mappedBy = "story")
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "story")
    private List<Studio> studios = new ArrayList<>();
    @Builder
    public Story(Long storyId, String storyTitle, String storyVideoUrl, String storyDesc, String storySummary, String storyThumbnailUrl, Work work, Category category, List<Scene> scenes, List<Studio> studios) {
        this.storyId = storyId;
        this.storyTitle = storyTitle;
        this.storyVideoUrl = storyVideoUrl;
        this.storyDesc = storyDesc;
        this.storySummary = storySummary;
        this.storyThumbnailUrl = storyThumbnailUrl;
        this.work = work;
        this.category = category;
        this.scenes = scenes;
        this.studios = studios;
//        this.storyLike = storyLike;
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void addStudio(Studio studio) {
        studios.add(studio);
    }

}
