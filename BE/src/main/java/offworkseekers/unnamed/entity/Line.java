package offworkseekers.unnamed.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "line")
@NoArgsConstructor
public class Line {

    @Id @GeneratedValue
    @Column(name = "line_id")
    private int id;

    @NonNull
    @Column(name = "line_timestamp")
    private String timestamp;

    @NonNull
    @Column(name = "line_script")
    private String script;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scene_id")
    private Scene scene;

    @Builder
    public Line(int id, @NonNull String timestamp, @NonNull String script, Scene scene) {
        this.id = id;
        this.timestamp = timestamp;
        this.script = script;
        this.scene = scene;
    }
}
