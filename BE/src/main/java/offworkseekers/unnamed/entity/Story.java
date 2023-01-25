package offworkseekers.unnamed.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Long id;
    @Column(name = "story_title")
    @Size(max = 15)
    private String title;

    @Column(name = "story_video_url")
    private String videoUrl;

    @Column(name = "story_desc")
    private String desc;

    @Column(name = "story_summary")
    private String summary;

    @Column(name = "story_thumbnail_url")
    private String thumbnailUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Work work;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "story")
    private List<Scene> scenes = new ArrayList<>();

    @OneToMany(mappedBy = "story")
    private List<Team> teams = new ArrayList<>();
    @Builder
    public Story(Long id, String title, String videoUrl, String desc, String summary, String thumbnailUrl, Work work, List<Scene> scenes, List<Team> teams) {
        this.id = id;
        this.title = title;
        this.videoUrl = videoUrl;
        this.desc = desc;
        this.summary = summary;
        this.thumbnailUrl = thumbnailUrl;
        this.work = work;
        this.category = category;
        this.scenes = scenes;
        this.teams = teams;
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

}
