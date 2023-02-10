package offworkseekers.unnamed.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Table(name = "line")
@NoArgsConstructor
public class Line {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id", columnDefinition = "INT UNSIGNED")
    private Long lineId;

    @NonNull
    @Column(name = "line_timestamp")
    private LocalTime lineTimestamp;

    @NonNull
    @Column(name = "line_script")
    private String lineScript;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scene_id")
    private Scene scene;

    @Builder
    public Line(Long lineId, @NonNull LocalTime lineTimestamp, @NonNull String lineScript, Scene scene) {
        this.lineId = lineId;
        this.lineTimestamp = lineTimestamp;
        this.lineScript = lineScript;
        this.scene = scene;
    }
}
