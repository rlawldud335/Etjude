package offworkseekers.unnamed.api.dto;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import offworkseekers.unnamed.db.entity.User;

@Data
@RequiredArgsConstructor
@ToString
public class StatusDTO {

    User user;
    int sceneNumber;
    String status;

    @Builder
    public StatusDTO(User user, int sceneNumber, String status) {
        this.user = user;
        this.sceneNumber = sceneNumber;
        this.status = status;
    }
}
