package offworkseekers.unnamed.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class StudioFilmListResponse {

    @JsonProperty(value = "film_id")
    private Long filmId;

    @JsonProperty(value = "film_created_date")
    private LocalDate filmCreatedDate;

    @JsonProperty(value = "film_end_date")
    private LocalDate filmEndDate;

    @JsonProperty(value = "film_video_url")
    private String filmVideoUrl;

    @Builder
    public StudioFilmListResponse(Long filmId, LocalDate filmCreatedDate, LocalDate filmEndDate, String filmVideoUrl) {
        this.filmId = filmId;
        this.filmCreatedDate = filmCreatedDate;
        this.filmEndDate = filmEndDate;
        this.filmVideoUrl = filmVideoUrl;
    }
}
