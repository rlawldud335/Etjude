package offworkseekers.unnamed.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import offworkseekers.unnamed.api.dto.SimpleUserDTO;

import java.time.LocalDate;
import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class StudioInfoResponse {

    @JsonProperty(value = "studio_id")
    private Long studioId;

    private String studioTitle;

    @JsonProperty(value = "studio_created_date")
    private LocalDate studioCreateDate;

    @JsonProperty(value = "studio_end_date")
    private LocalDate studioEndDate;

    @JsonProperty(value = "story_id")
    private Long storyId;

    private String storyTitle;

    private String storyVideoUrl;

    @JsonProperty(value = "captain_id")
    private String captainId;

    @JsonProperty(value = "member_list")
    List<SimpleUserDTO> memberList;

    @Builder
    public StudioInfoResponse(Long studioId, String studioTitle, LocalDate studioCreateDate, LocalDate studioEndDate, Long storyId, String storyTitle, String storyVideoUrl, List<SimpleUserDTO> memberList, String captainId) {
        this.studioId = studioId;
        this.studioTitle = studioTitle;
        this.studioCreateDate = studioCreateDate;
        this.studioEndDate = studioEndDate;
        this.storyId = storyId;
        this.storyTitle = storyTitle;
        this.storyVideoUrl = storyVideoUrl;
        this.captainId = captainId;
        this.memberList = memberList;
    }
}
