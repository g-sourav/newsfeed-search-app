package com.news.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	Logger log=LoggerFactory.getLogger(JwtGenerator.class);
	@Value("${mysecretKey.key}")
	private String secretkey;
	
	@Value("${time.expirationTime}")
	private long expirationTime;
	
	public String generateToken(Authentication authentication){
		User user= (User) authentication.getPrincipal();
		String token= Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+expirationTime))
				.signWith(SignatureAlgorithm.HS512, secretkey)
				.compact();
		return token;
	}
	public boolean validateToken(String token){

		try{
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
			return true;
		}
		catch(Exception ex){
			log.error("JwtToken validation error->{}", ex.getMessage());
		}
		return false;
	}
	public String getUsernameFromToken(String token){

		return Jwts.parser()
				.setSigningKey(secretkey)
				.parseClaimsJws(token)
				.getBody().getSubject();
	}
}
