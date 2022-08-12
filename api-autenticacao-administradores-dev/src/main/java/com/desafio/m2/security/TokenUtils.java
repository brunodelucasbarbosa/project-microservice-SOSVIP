package com.desafio.m2.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.desafio.m2.model.Admin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {

	private static final int SEGUNDOS = 1000;
	private static final int MINUTOS = 60 * SEGUNDOS;
	private static final int HORAS = 60 * MINUTOS;
	private static final int DIAS = 24 * HORAS;

	private static final String HEADER = "Authorization";
	private static final String PREFIX = System.getenv("TOKEN_PREFIX") + " ";
	private static final long EXPIRATION =
			(SEGUNDOS *  Integer.parseInt(System.getenv("EXPIRATION_SECONDS"))) +
					(MINUTOS * Integer.parseInt(System.getenv("EXPIRATION_MINUTES"))) +
					(HORAS * Integer.parseInt(System.getenv("EXPIRATION_HOURS"))) +
					(DIAS * Integer.parseInt(System.getenv("EXPIRATION_DAYS")));

	private static final String SECRET_KEY = System.getenv("SECRET_KEY");
	private static final String EMISSOR = System.getenv("EMISSOR");

	public static String createToken(Admin administrador) {
		Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


		String token = Jwts.builder()
				.setSubject(administrador.getEmail()+',' + administrador.getId())
				.setIssuer(EMISSOR)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(secretKey, SignatureAlgorithm.HS256)
				.compact();
		return PREFIX + token;
	}

	private static boolean isExpirationValid(Date expiration) {
		return expiration.after(new Date(System.currentTimeMillis()));
	}

	private static boolean isEmissorValid(String emissor) {
		return emissor.equals(EMISSOR);
	}

	private static boolean isSubjectValid(String username) {
		return username != null && username.length() > 0;
	}

	public static Authentication validate(HttpServletRequest request) {

		String token = request.getHeader(HEADER);
		token = token.replace(PREFIX, "");
		try {
			Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token);

			String username = jwsClaims.getBody().getSubject();
			String issuer = jwsClaims.getBody().getIssuer();
			Date expira = jwsClaims.getBody().getExpiration();

			if (isSubjectValid(username) && isEmissorValid(issuer) && isExpirationValid(expira)) {

				return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
			}
			return null;
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public static Boolean validateTokenAPis(String tokenRecebido) {

		try {
			String token = tokenRecebido.replace(PREFIX, "");
			Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token);
			//isTokenValido(token);
			String username = jwsClaims.getBody().getSubject();
			String issuer = jwsClaims.getBody().getIssuer();
			Date expira = jwsClaims.getBody().getExpiration();
			if (isSubjectValid(username) && isEmissorValid(issuer) && isExpirationValid(expira)) {
				System.err.println("permitindo acesso");
				return true;
			}
			System.err.println("bloqueando acesso!");
			return false;
		}catch(Exception e){
			System.err.println(e.getMessage());
			return false;
		}


	}

//	public static boolean isTokenValido(String authToken) {
//	    try {
//	        Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
//	    	System.err.println("Tudo OK");
//	        return true;
//	    } catch (SignatureException ex) {
//	        //return ResponseEntity(new ErroResponseDto("Forbidden", Collections.singletonList("Invalid JWT signature")), HttpStatus.FORBIDDEN);
//	    	System.err.println("Invalid JWT signature");
//	        //throw new InvalidJwtAuthenticationException("Invalid JWT signature");
//	    } catch (MalformedJwtException ex) {
//	    	System.err.println("Invalid JWT token");
//	        //throw new InvalidJwtAuthenticationException("Invalid JWT token");
//	    } catch (ExpiredJwtException ex) {
//	    	System.err.println("Expired JWT token");
//	        //throw new InvalidJwtAuthenticationException("Expired JWT token");
//	    } catch (UnsupportedJwtException ex) {
//	    	System.err.println("Unsupported JWT token");
//	        //throw new InvalidJwtAuthenticationException("Unsupported JWT token");
//	    } catch (IllegalArgumentException ex) {
//	    	System.err.println("JWT claims string is empty.");
//	        //throw new InvalidJwtAuthenticationException("JWT claims string is empty.");
//	    }
//	    return false;
//	}


}