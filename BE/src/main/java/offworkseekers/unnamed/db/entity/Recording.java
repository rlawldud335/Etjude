package offworkseekers.unnamed.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "recording")
@NoArgsConstructor
@ToString
public class Recording {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recording_id", columnDefinition = "INT UNSIGNED")
    private Long recordingId;

    @Column(name = "recording_video_url")
    private String recordingVideoUrl;

    @Column(name = "recording_user_id")
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scene_id")
    private Scene scene;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @Builder
    public Recording(Long recordingId, String recordingVideoUrl, String userId, Scene scene, Studio studio) {
        this.recordingId = recordingId;
        this.recordingVideoUrl = recordingVideoUrl;
        this.userId = userId;
        this.scene = scene;
        this.studio = studio;
    }
}
