package com.example.javasb_taskms.components;

import com.example.javasb_taskms.models.BearerToken;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import lombok.val;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtSupport {

    private final String keyString = "9rSPQFeqLUhTtn9haBaWzPNMKiF6tVxkJhSd8sH7hyk=";
    private final byte[] keyBytes = keyString.getBytes();
    private final SecretKey key = Keys.hmacShaKeyFor(keyBytes);
    private final JwtParser parser = Jwts.parser().verifyWith(key).build();

    public BearerToken generate(String username) {
        val builder = Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(key);
        return new BearerToken(builder.compact());
    }

    public String getUsername(BearerToken token) {
        return parser.parseSignedClaims(token.getValue()).getPayload().getSubject();
    }

    public Boolean isTokenUnexpired(BearerToken token) {
        val claims = parser.parseSignedClaims(token.getValue()).getPayload();
        return !claims.getExpiration().before(Date.from(Instant.now()));
    }

}
