package dev.mauricioize.nutrition_calculator_api.shared.security;

import java.security.Key;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private final Key key;

    public JwtService(@Value("${jwt.secret}") String SECRET) {
        this.key = Keys.hmacShaKeyFor(
                SECRET.getBytes());
    }

    // AuthService calls this and sends the email as subject
    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new java.util.Date())
                // TODO: Read expiration from config
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24))
                .signWith(key)
                .compact();
    }

    public Optional<Claims> validate(String token) {
        try {
            Claims c = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.of(c);
        } catch (JwtException ex) {
            return Optional.empty();
        }
    }

    public Optional<String> extractSubject(String token) {
        return validate(token).map(Claims::getSubject);
    }
}
