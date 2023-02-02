package offworkseekers.unnamed.api.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class MyPageFilmsWithMembersResponse {
    MyPageFilmsResponse myPageFilmsResponse;
    List<String> teamMembers;

    public MyPageFilmsWithMembersResponse(MyPageFilmsResponse myPageFilmsResponse, List<String> teamMembers) {
        this.myPageFilmsResponse = myPageFilmsResponse;
        this.teamMembers = teamMembers;
    }
}
