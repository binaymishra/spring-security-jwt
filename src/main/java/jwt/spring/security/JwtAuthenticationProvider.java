package jwt.spring.security;

import java.util.function.Function;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author Binay Mishra
 *
 */
public class JwtAuthenticationProvider implements AuthenticationProvider{

	private String secreteKey;

	public JwtAuthenticationProvider(String secreteKey) {
		this.secreteKey = secreteKey;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
		//username = user and roles = USER, ADMIN
		token = new JwtAuthenticationToken(username.apply(token.getToken()),AuthorityUtils.commaSeparatedStringToAuthorityList(roles.apply(token.getToken())));
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

	final Function<String, String> username = jwt -> {
		 final Claims body = (Claims) Jwts.parser().setSigningKey(secreteKey).parse(jwt).getBody();
		return body.get("username", String.class);
	};

	final Function<String, String> roles = jwt -> {
		 final Claims body = (Claims) Jwts.parser().setSigningKey(secreteKey).parse(jwt).getBody();
		return body.get("roles", String.class);
	};

}
