package f3f.core;

import f3f.repository.PilotRepository;
import f3f.repository.PilotService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages={
		"f3f"})
public class F3fCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(F3fCoreApplication.class, args);

	}
}
