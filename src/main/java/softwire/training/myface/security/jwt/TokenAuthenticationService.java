package softwire.training.myface.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import softwire.training.myface.CustomException;
import softwire.training.myface.security.UserPrincipal;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenAuthenticationService {


    private long EXPIRATIONTIME = 1000 * 60 * 15; // 15 min
    private String secret = System.getenv("JWT_AUTH_KEY");
    private String tokenPrefix = "Bearer";
    private String headerString = "Authorization";
    private MyFaceUserDetailsService myFaceUserDetailsService;

    @Autowired
    public TokenAuthenticationService(MyFaceUserDetailsService myFaceUserDetailsService) {
        this.myFaceUserDetailsService = myFaceUserDetailsService;
    }

    public String addAuthentication(String username) {

        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + EXPIRATIONTIME);
        // We generate a token now.
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public Authentication getAuthentication(String token) {
        UserPrincipal userDetails = (UserPrincipal) myFaceUserDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


