package offworkseekers.unnamed;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 스케줄러 사용을 위해 추가
@SpringBootApplication
public class UnnamedApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:hidden.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(UnnamedApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
