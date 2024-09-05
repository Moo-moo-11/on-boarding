package moomoo.onboarding.infra.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtPlugin {

    private final String issuer;
    private final SecretKey key;
    private final long accessTokenExpirationHour;

    public JwtPlugin(
            @Value("${auth.jwt.issuer}") String issuer,
            @Value("${auth.jwt.secret}") String secret,
            @Value("${auth.jwt.accessTokenExpirationHour}") long accessTokenExpirationHour
    ) {
        this.issuer = issuer;
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpirationHour = accessTokenExpirationHour;
    }

    public String generateAccessToken(String subject, String role) {
        return generateToken(subject, role, Duration.ofHours(accessTokenExpirationHour));
    }

    private String generateToken(String subject, String role, Duration expirationPeriod) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(subject)
                .issuer(issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expirationPeriod)))
                .claim("role", role)
                .signWith(key)
                .compact();
    }
}
