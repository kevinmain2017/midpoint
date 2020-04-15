package fis.com.vn.jwt;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 86_400_000; // 1 days
    
    static final String SECRET = "e01e3a5a-bd62-4415-8638-a28f175632ba";
     
    static final String TOKEN_PREFIX = "Bearer";
     
    static final String HEADER_STRING = "Authorization";
 
    public static void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder().setSubject(username)
//        		.claim("role", "[{a:'b'}]")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }
 
    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        System.out.println("token: "+token);
        if (token != null) {
            try {
            	// parse the token.
            	long dateExp = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getExpiration().getTime();
            	
            	if(dateExp <= System.currentTimeMillis()) {
            		return null;
            	}
            	
                String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
                        .getSubject();
     
                return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
			} catch (Exception e) {
				// TODO: handle exception
			}
        }
        return null;
    }
}
