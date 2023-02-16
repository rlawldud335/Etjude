package offworkseekers.unnamed.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import offworkseekers.unnamed.api.dto.StoryLineDto;

import java.util.List;


@Getter
@ToString
public class RoleWithLineOfSceneResponse {

    private Long roleId;
    private String rolePhotoUrl;
    private String roleName;
    private Long sceneId;
    private int sceneNumber;
    private String sceneFileName;
    private List<StoryLineDto> lines;

    @Builder
    public RoleWithLineOfSceneResponse(Long roleId, String rolePhotoUrl, String roleName, Long sceneId, int sceneNumber, List<StoryLineDto> lines, String sceneFileName) {
        this.roleId = roleId;
        this.rolePhotoUrl = rolePhotoUrl;
        this.roleName = roleName;
        this.sceneId = sceneId;
        this.sceneNumber = sceneNumber;
        this.sceneFileName = sceneFileName;
        this.lines = lines;
    }
}
