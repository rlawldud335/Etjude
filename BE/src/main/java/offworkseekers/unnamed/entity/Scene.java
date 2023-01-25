package offworkseekers.unnamed.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "scene")
@NoArgsConstructor
public class Scene {

    @Id @GeneratedValue
    @Column(name = "scene_id")
    private int id;

    @NonNull
    @Column(name = "scene_number")
    private int number;

    @NonNull
    @Column(name = "scene_timestamp")
    private String timestamp;

    @NonNull
    @Column(name = "scene_file_name")
    private String fileName;

    @OneToMany(mappedBy = "scene")
    private List<Line> lines = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @Builder
    public Scene(int id, @NonNull int number, @NonNull String timestamp, @NonNull String fileName, List<Line> lines, Role role) {
        this.id = id;
        this.number = number;
        this.timestamp = timestamp;
        this.fileName = fileName;
        this.lines = lines;
        this.role = role;
    }
}
