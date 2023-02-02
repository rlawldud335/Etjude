package offworkseekers.unnamed.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalTime;


@Getter
@ToString
public class StoryLineDto {

    private LocalTime lineTimeStamp;
    private String line;

    @Builder
    public StoryLineDto(LocalTime lineTimeStamp, String line) {
        this.lineTimeStamp = lineTimeStamp;
        this.line = line;
    }
}
