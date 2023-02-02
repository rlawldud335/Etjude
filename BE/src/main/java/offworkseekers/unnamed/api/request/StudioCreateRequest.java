package offworkseekers.unnamed.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import offworkseekers.unnamed.db.entity.Studio;

import java.time.LocalDate;
import java.util.List;

@Getter
public class StudioCreateRequest {

    @JsonProperty(value = "user_id")
    private String captainId;

    @JsonProperty(value = "studio_title")
    private String studioTitle;

    @JsonProperty(value = "team_member_Ids")
    private List<String> teamMembersIds;

    @JsonProperty(value = "story_id")
    private Long storyId;

    public Studio toStudioEntity() {
        return Studio.builder()
                .captainId(captainId)
                .studioTitle(studioTitle)
                .studioEndDate(LocalDate.now().plusDays(7))
                .studioRepository("get Repository Method")
                .build();
    }

}
