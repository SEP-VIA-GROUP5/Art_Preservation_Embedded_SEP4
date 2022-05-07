package dk.via.sep4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sep4Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Sep4Application.class);
		app.run(args);
	}

}
