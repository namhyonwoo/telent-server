package kuya.talent.common.infra;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${jwt.token-validity-in-seconds}")
    private Long tokenValidityInSeconds;

    private final SecretKey secretKey;

    public TokenProvider(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret));
    }

    public String createToken(String userId, String userName) {
        return Jwts.builder()
                .claims()
                .add("userId", userId)
                .add("userName", userName)
                .and()
                .issuer(issuer)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + this.tokenValidityInSeconds * 1000))
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public void verifyToken(String jwtToken) {
        try {
            Jwts.parser().verifyWith(secretKey).build()
                    .parseSignedClaims(jwtToken);
        } catch (Exception e) {
            log.error("verifyToken error", e);
        }
    }
}
