package br.com.sosvip.apigerenciamentoclientes.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


	@Override
	    public void configure(HttpSecurity httpSec) throws Exception {
		   	
	        httpSec.csrf().disable()
					.exceptionHandling().and()
					.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
					.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
	                .and().cors();

	        httpSec.addFilterBefore(new Filters(), UsernamePasswordAuthenticationFilter.class);

	    }

	@Override
	public void configure(final WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers(
				"/v2/api-docs",
				"/swagger-resources/**",
				"/swagger-ui.html",
				"/webjars/**" ,
				"/swagger.json");
	}
	
}