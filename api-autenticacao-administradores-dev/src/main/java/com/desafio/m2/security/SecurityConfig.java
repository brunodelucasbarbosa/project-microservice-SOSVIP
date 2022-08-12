package com.desafio.m2.security;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private EntryPoint entryPoint;

	public void configure(HttpSecurity httpSec) throws Exception {
		httpSec.csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint(entryPoint).and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/admin/login").permitAll()
		.antMatchers(HttpMethod.POST,"/admin").permitAll()
		.antMatchers(HttpMethod.GET,"/admin/verificaToken").permitAll()
		.antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
		.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
		.anyRequest().authenticated().and().cors();

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