package jwt.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binay Mishra
 *
 */
@RestController
@SpringBootApplication
public class Application {

	@GetMapping
	String home(){
		return "/";
	}

	@GetMapping("/api")
	String api(){
		return "/api";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
