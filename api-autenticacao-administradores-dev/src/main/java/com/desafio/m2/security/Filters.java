package com.desafio.m2.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class Filters extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getHeader("Authorization") != null) {
			Authentication auth = TokenUtils.validate(request);
			if(auth != null) {
				System.out.println("Token válido");
				SecurityContextHolder.getContext().setAuthentication(auth);
			}else{
				System.out.println("Token inválido");
			}

			
		}

		filterChain.doFilter(request, response);

	}

}




