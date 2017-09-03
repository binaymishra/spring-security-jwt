package jwt.spring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	protected JwtAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler successHandler) {
		super("/api/**");
		setAuthenticationSuccessHandler(successHandler);
		setAuthenticationManager(authenticationManager);
	}

	@Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		String header = request.getHeader("Authorization");

        if (StringUtils.isEmpty(header) || !header.startsWith("Bearer "))
			throw new AuthenticationCredentialsNotFoundException("No JWT token found in request headers");

        String authToken = header.substring(7);

        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

        return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
				super.successfulAuthentication(request, response, chain, authResult);
		// As this authentication is in HTTP header, after success we need to continue the request normally
		// and return the response as if the resource was not secured at all
		chain.doFilter(request, response);
	}

}
