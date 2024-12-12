package com.AttendanceTracker.Prelag.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generate a secure key
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    /**
     * Generates a JWT token for the given email.
     *
     * @param email The email for which the token is generated.
     * @return The generated JWT token.
     */
    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    /**
     * Validates a JWT token against the provided email.
     *
     * @param token The JWT token to validate.
     * @param email The email to match against the token's subject.
     * @return True if the token is valid, false otherwise.
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token); // Validates signature and expiration
            return true;
        } catch (Exception e) {
            return false; // Invalid token (expired, malformed, etc.)
        }
    }

    // Extracts the email from the token
    public static String extractEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
