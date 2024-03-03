package com.eZonWork.JwtService;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.eZonWork.Utility.AuthResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	/* author:: PRAVEEN KUMAR
	 * FOR JWT TOKEN GENERATION
	 *  */
	
	private final static String SECRETE_KEY="6c451a396fd3b44c57dd8e9d19be20504981af4b06c20873903400ede0e725cc378899cd42dfa62faa7f343bcebabf4fdca394549d2e7a5d66b5cb3dba43f507";
	private final static String ISSUER="Praveen Kumar Yakasiri";
	
	public AuthResponse generateTokens(String userName)
	{
		AuthResponse authResponse=new AuthResponse();
		authResponse.setAccessToken(this.generateAccessToken(userName));
		authResponse.setRefreshToken(this.generateRefreshToken(userName));
		return authResponse;
	}
	
	public String generateAccessToken(String userName) {
		return createAccessToken(new HashMap<>(),userName);
	}

	public String createAccessToken(HashMap<String,Object> claims, String userName) {
		// TODO Auto-generated method stub
		return Jwts.builder()
				.setIssuer(ISSUER)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*5))
				.signWith(getSignKey(),SignatureAlgorithm.HS512)
				.compact();
	}
	
	public String generateRefreshToken(String userName) {
		return createRefreshToken(new HashMap<>(),userName);
	}

	public String createRefreshToken(HashMap<String,Object> claims, String userName) {
		// TODO Auto-generated method stub
		return Jwts.builder()
				.setIssuer(ISSUER)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
				.signWith(getSignKey(),SignatureAlgorithm.HS512)
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
