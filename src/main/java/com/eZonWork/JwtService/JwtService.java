package com.eZonWork.JwtService;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	private final static String SECRETE_KEY="5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	private final static String ISSUER="Praveen";
	
	public String generateToken(String userName) {
		return createToken(new HashMap<>(),userName);
	}

	public String createToken(HashMap<String,Object> claims, String userName) {
		// TODO Auto-generated method stub
		return Jwts.builder()
//				.setIssuer(ISSUER)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*3))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public Key getSignKey() {
		byte[] bs=Decoders.BASE64.decode(SECRETE_KEY);
		return Keys.hmacShaKeyFor(bs);
	}
	
	public <T> T getClaims(String token,Function<Claims, T> claFunction) {
		final Claims claims=getAllClaims(token);
		return claFunction.apply(claims);
	}
	
	private Claims getAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public String getUserFromToken(String token) {
		return getClaims(token,Claims::getSubject);
	}
	
	public Date getExpireFromToken(String token) {
		return getClaims(token, Claims::getExpiration);
	}
	
	public boolean isTokenValid(String token,UserDetails details) {
		final String userName=getUserFromToken(token);
		return (userName.equals(details.getUsername()));
	}
	
	public boolean isTokenExpire(String token) {
		return getExpireFromToken(token).before(new Date());
	}

}
