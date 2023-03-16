package slfdemo.sidecar.springboot.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import slfdemo.sidecar.springboot.Constants;
import java.util.Date;

@NoArgsConstructor
@Component
public class JwtBuilder {

    public String generateToken(int userId, String username){

        return Jwts.builder()
                .claim("userId", userId)
                .claim("username", username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constants.getTokenValidity()))
                .signWith(SignatureAlgorithm.HS256, Constants.getTokenKey())
                .compact();
    }


}
