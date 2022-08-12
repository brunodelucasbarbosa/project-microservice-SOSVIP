package br.com.sosvip.apigerenciamentoclientes.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

public class Filters extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (request.getHeader("Authorization") == null || request.getHeader("Authorization") == "") {
			response.sendError(403, "Token inválido!");
			return;
		}
		if (!request.getHeader("Authorization").startsWith(System.getenv("TOKEN_PREFIX"))){
			response.sendError(403, "Token inválido!");
			return;
		}

		String theUrl = System.getenv("TOKEN_VERIFY_URL");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseValidaToken;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", request.getHeader("Authorization"));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			responseValidaToken = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class);
			System.out.println("Result - status ( "+ responseValidaToken.getStatusCodeValue() + " ) has message: " + responseValidaToken.getBody());
			if(responseValidaToken.getStatusCodeValue() != 200) {
				response.sendError(responseValidaToken.getStatusCodeValue(), responseValidaToken.getBody());
				return;
			}
		}
		catch (Exception e) {
			System.out.println("*Exception: "+ e.getMessage());
			response.sendError( 403 ,"Acesso negado!");
			e.printStackTrace();
			return;
		}

		
		filterChain.doFilter(request, response);
		
	}


	
}

