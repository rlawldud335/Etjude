package offworkseekers.unnamed.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class StudioSettingResponse {

    @JsonProperty(value = "studio_title")
    private String studioTitle;

    @JsonProperty(value = "studio_created_date")
    private LocalDate studioCreateDate;

    @JsonProperty(value = "studio_end_date")
    private LocalDate studioEndDate;

    private String nickname;

    @JsonProperty(value = "user_photo_url")
    private String userPhotoUrl;

    @Builder
    public StudioSettingResponse(String studioTitle, LocalDate studioCreateDate, LocalDate studioEndDate, String nickname, String userPhotoUrl) {
        this.studioTitle = studioTitle;
        this.studioCreateDate = studioCreateDate;
        this.studioEndDate = studioEndDate;
        this.nickname = nickname;
        this.userPhotoUrl = userPhotoUrl;
    }
}
