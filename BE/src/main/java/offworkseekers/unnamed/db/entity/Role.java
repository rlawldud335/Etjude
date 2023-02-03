package offworkseekers.unnamed.db.entity;

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

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;

    @NonNull
    @Column(name = "role_name")
    private String roleName;

    @NonNull
    @Column(name = "role_desc")
    private String roleDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @NonNull
    @Column(name = "role_photo_url")
    private String rolePhotoUrl;

    @OneToMany(mappedBy = "role")
    private List<Scene> scenes = new ArrayList<>();

    @Builder
    public Role(Long roleId, @NonNull String roleName, @NonNull String roleDesc, Story story, @NonNull String rolePhotoUrl, List<Scene> scenes) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.story = story;
        this.rolePhotoUrl = rolePhotoUrl;
        this.scenes = scenes;
    }
}
