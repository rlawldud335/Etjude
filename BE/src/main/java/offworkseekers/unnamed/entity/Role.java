package offworkseekers.unnamed.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@NoArgsConstructor
public class Role {

    @Id @GeneratedValue
    @Column(name = "role_id")
    private int id;

    @NonNull
    @Column(name = "role_name")
    private String name;

    @NonNull
    @Column(name = "role_desc")
    private String desc;

    @Column(name = "story_id")
    private int storyId;

    @NonNull
    @Column(name = "role_photo_url")
    private String photoUrl;

    @OneToMany(mappedBy = "role")
    private List<Scene> scenes = new ArrayList<>();

    @Builder
    public Role(int id, @NonNull String name, @NonNull String desc, @NonNull int storyId, @NonNull String photoUrl, List<Scene> scenes) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.storyId = storyId;
        this.photoUrl = photoUrl;
        this.scenes = scenes;
    }
}
