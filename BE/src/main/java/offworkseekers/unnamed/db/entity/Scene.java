package offworkseekers.unnamed.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "scene")
@NoArgsConstructor
public class Scene {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scene_id", columnDefinition = "INT UNSIGNED")
    private Long sceneId;

    @Column(name = "scene_number")
    private int sceneNumber;

    @NonNull
    @Column(name = "scene_timestamp")
    private LocalTime sceneTimestamp;

    @NonNull
    @Column(name = "scene_file_name")
    private String sceneFileName;

    @OneToMany(mappedBy = "scene")
    private List<Line> lines = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @Builder
    public Scene(Long sceneId, int sceneNumber, @NonNull LocalTime sceneTimestamp, @NonNull String sceneFileName, List<Line> lines, Role role, Story story) {
        this.sceneId = sceneId;
        this.sceneNumber = sceneNumber;
        this.sceneTimestamp = sceneTimestamp;
        this.sceneFileName = sceneFileName;
        this.lines = lines;
        this.role = role;
        this.story = story;
    }
}
