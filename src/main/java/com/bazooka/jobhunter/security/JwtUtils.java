package com.bazooka.jobhunter.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.bazooka.jobhunter.entity.User;

import static com.bazooka.jobhunter.security.SecurityConstants.EXPIRATION_TIME;
import static com.bazooka.jobhunter.security.SecurityConstants.SECRET;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	public String generateToken(Authentication authentication) {

		User user = (User) authentication.getPrincipal();
		Date now = new Date(System.currentTimeMillis());
		Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

		Map<String, Object> claims = new HashMap<>();
		claims.put("id", (Long.toString(user.getId())));
		claims.put("username", user.getUsername());
		claims.put("firstName", user.getFirstName());
		claims.put("lastName", user.getLastName());

		return Jwts.builder()
				.setSubject(user.getUsername())
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	public String getUserNameFromJwtToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		return (String) claims.get("username");
	}

}
