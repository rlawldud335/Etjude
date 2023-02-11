package offworkseekers.unnamed;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 스케줄러 사용을 위해 추가
@SpringBootApplication
public class UnnamedApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnnamedApplication.class, args);
	}

}
