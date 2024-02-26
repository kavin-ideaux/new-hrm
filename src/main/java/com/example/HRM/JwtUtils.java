package com.example.HRM;

import com.example.HRM.entity.admin.AdminLogin;
import com.example.HRM.entity.clientDetails.ClientProfile;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
public class JwtUtils {
	private static final String SECRET = "TE7koozHpSsNbW2P3COvvWC7umIzVqOIO/6RNLmWVl4=";
	  
	  private static final long EXPIRATION_TIME = 864000000L;
	  
	  public static String generateToken(AdminLogin admin) {
	    Map<String, Object> claims = new HashMap<>();
	    return createToken(claims, admin.getEmail());
	  }
	  
	  public static String generateClientToken(ClientProfile profile) {
	    Map<String, Object> claims = new HashMap<>();
	    return createToken(claims, profile.getEmail());
	  }
	  
	  private static String createToken(Map<String, Object> claims, String subject) {
	    Date now = new Date();
	    Date expirationDate = new Date(now.getTime() + 864000000L);
	    return Jwts.builder()
	      .setClaims(claims)
	      .setSubject(subject)
	      .setIssuedAt(now)
	      .setExpiration(expirationDate)
	      .signWith(SignatureAlgorithm.HS256, "TE7koozHpSsNbW2P3COvvWC7umIzVqOIO/6RNLmWVl4=")
	      .compact();
	  }
	  
	  public static boolean validateToken(String token, AdminLogin admin) {
	    String email = extractUsername(token);
	    return (email.equals(admin.getEmail()) && !isTokenExpired(token));
	  }
	  
	  public static boolean validateClientToken(String token, ClientProfile profile) {
	    String email = extractUsername(token);
	    return (email.equals(profile.getEmail()) && !isTokenExpired(token));
	  }
	  
	  private static String extractUsername(String token) {
	    return extractClaim(token, Claims::getSubject);
	  }
	  
	  private static Date extractExpiration(String token) {
	    return extractClaim(token, Claims::getExpiration);
	  }
	  
	  private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	    Claims claims = (Claims)Jwts.parser().setSigningKey("TE7koozHpSsNbW2P3COvvWC7umIzVqOIO/6RNLmWVl4=").parseClaimsJws(token).getBody();
	    return claimsResolver.apply(claims);
	  }
	  
	  private static boolean isTokenExpired(String token) {
	    Date expiration = extractExpiration(token);
	    return expiration.before(new Date());
	  }
	}

