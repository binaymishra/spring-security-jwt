package jwt.spring.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Binay Mishra
 *
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken{

	private static final long serialVersionUID = 1L;

	private String token;
	private Object username, password;

	public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object username,
			Object password) {
		super(authorities);
		this.username = username;
		this.password = password;
	}

	public JwtAuthenticationToken(Object username,
			Object password) {
		super(null);
		this.username = username;
		this.password = password;
	}

	public JwtAuthenticationToken(String token) {
		super(null);
		this.token = token;
	}
	public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
	}


	@Override
	public Object getCredentials() {
		return password;
	}


	@Override
	public Object getPrincipal() {
		return username;
	}

	public String getToken() {
		return token;
	}
}
