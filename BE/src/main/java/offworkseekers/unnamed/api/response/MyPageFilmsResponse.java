package offworkseekers.unnamed.api.response;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class MyPageFilmsResponse {
    Long filmId;
    String categoryName;
    String workTitle;
    String storyTitle;
    String studioTitle;
    String filmVideoUrl;
    LocalDate filmCreatedDate;
    LocalDate filmEndDate;
//    List<String> teamMembers;

    public MyPageFilmsResponse(Long filmId, String categoryName, String workTitle, String storyTitle, String studioTitle, String filmVideoUrl, LocalDate filmCreatedDate, LocalDate filmEndDate) {
        this.filmId = filmId;
        this.categoryName = categoryName;
        this.workTitle = workTitle;
        this.storyTitle = storyTitle;
        this.studioTitle = studioTitle;
        this.filmVideoUrl = filmVideoUrl;
        this.filmCreatedDate = filmCreatedDate;
        this.filmEndDate = filmEndDate;
    }


    //    public MyPageFilmsResponse(Long filmId, String categoryName, String workTitle, String storyTitle, String teamName, String filmVideoUrl, LocalDate filmCreatedDate, LocalDate filmEndDate) {
//        this.filmId = filmId;
//        this.categoryName = categoryName;
//        this.workTitle = workTitle;
//        this.storyTitle = storyTitle;
//        this.teamName = teamName;
//        this.filmVideoUrl = filmVideoUrl;
//        this.filmCreatedDate = filmCreatedDate;
//        this.filmEndDate = filmEndDate;
//    }


//    public MyPageFilmsResponse(Long filmId, String categoryName, String workTitle, String storyTitle, String teamName, String filmVideoUrl, LocalDate filmCreatedDate, LocalDate filmEndDate, List<String> teamMembers) {
//        this.filmId = filmId;
//        this.categoryName = categoryName;
//        this.workTitle = workTitle;
//        this.storyTitle = storyTitle;
//        this.teamName = teamName;
//        this.filmVideoUrl = filmVideoUrl;
//        this.filmCreatedDate = filmCreatedDate;
//        this.filmEndDate = filmEndDate;
//        this.teamMembers = teamMembers;
//    }
}
