package offworkseekers.unnamed.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class MyFilmListResponse {
    Long filmId;
    String categoryName;
    String workTitle;
    String storyTitle;
    List<String> teamMembers = new ArrayList<>();
    String filmVideoUrl;
    LocalDate filmCreatedDate;

    @Builder
    public MyFilmListResponse(Long filmId, String categoryName, String workTitle, String storyTitle, String filmVideoUrl, LocalDate filmCreatedDate, List<String> teamMembers) {
        this.filmId = filmId;
        this.categoryName = categoryName;
        this.workTitle = workTitle;
        this.storyTitle = storyTitle;
        this.filmVideoUrl = filmVideoUrl;
        this.filmCreatedDate = filmCreatedDate;
        this.teamMembers = teamMembers;
    }

    public void addTeamMember(String userName){
        teamMembers.add(userName);
    }
    public void addWorkTitle(String workTitle){
        this.workTitle = workTitle;
    }
}
