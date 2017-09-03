package jwt.spring.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${jwt.secreteKey}")
	private String secreteKey;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable() // because there is no form based login.
			.sessionManagement().disable() // we want to stateless.
				.exceptionHandling().authenticationEntryPoint((request, response, authException) ->
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")); // we want to handle it via response code

		http.authorizeRequests()
			.antMatchers("/", "/token").permitAll()
			.antMatchers("/api/**").authenticated() // securing /api/** URL
		.and().addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		http.authenticationProvider(jwtAuthenticationProvider());
	}

	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception{
		return new JwtAuthenticationFilter(authenticationManagerBean(), (request, response, authentication) -> { /**/ });
	}

	@Bean
	JwtAuthenticationProvider jwtAuthenticationProvider(){
		return new JwtAuthenticationProvider(secreteKey);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}