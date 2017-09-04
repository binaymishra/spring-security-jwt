package jwt.spring.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Binay Mishra
 *
 */
@RestController
@SpringBootApplication
public class Application {

	@Value("${jwt.secreteKey}")
	private String secreteKey;

	@GetMapping
	String home(){
		return "/";
	}

	@GetMapping("/api")
	String api(){
		return "/api";
	}

	@GetMapping("/token")
	String token(){
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", "user");
		claims.put("roles",    "USER, ADMIN");
		return "Bearer "+Jwts.builder().signWith(SignatureAlgorithm.HS256, secreteKey)
				.setClaims(claims)
				.compact();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
