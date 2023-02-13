package offworkseekers.unnamed.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SocketDTO {
    // 현재 스튜디오 번호를 저장하기 위한 변수
    private String studioId;
    
    // 유저의 아이디를 저장하기 위한 변수
    private String userId;

    // 유저의 이름을 저장하기 위한 변수
    private String nickname;

    // 메시지의 내용을 저장하기 위한 변수
    private String content;

    // 메시지가 보내진 시간을 저장하기 위한 변수
    private String chatTime;
}