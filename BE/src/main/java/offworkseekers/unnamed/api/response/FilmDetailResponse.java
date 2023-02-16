package offworkseekers.unnamed.api.response;

import lombok.Builder;
import lombok.Data;
import offworkseekers.unnamed.db.entity.Comment;
import offworkseekers.unnamed.db.entity.TeamMember;
import offworkseekers.unnamed.db.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class FilmDetailResponse {
    String articleTitle;
    String articleContent;
    LocalDate articleCreatedDate;
    String writerId;
    String writerNickName;
    String writerPhotoUrl;
    String filmVideoUrl;
    String workTitle;
    String storyTitle;
    String studioTitle;
    List<String> teamMembers = new ArrayList<>();
    List<FilmCommentResponse> comments = new ArrayList<>();

    @Builder
    public FilmDetailResponse(String articleTitle, String articleContent, LocalDate articleCreatedDate, String writerId, String writerNickName, String writerPhotoUrl, String filmVideoUrl, String studioTitle){
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleCreatedDate = articleCreatedDate;
        this.writerId = writerId;
        this.writerNickName = writerNickName;
        this.writerPhotoUrl = writerPhotoUrl;
        this.filmVideoUrl = filmVideoUrl;
        this.studioTitle = studioTitle;

    }

    public void addStoryTitle(String storyTitle){
        this.storyTitle = storyTitle;
    }
    public void addWorkTitle(String workTitle){
        this.workTitle = workTitle;
    }

    public void addTeamMember(String userName){
        teamMembers.add(userName);
    }

    public void addComment(FilmCommentResponse comment){
        comments.add(comment);
    }

}
