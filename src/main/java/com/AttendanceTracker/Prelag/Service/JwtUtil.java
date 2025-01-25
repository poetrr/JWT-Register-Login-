package com.AttendanceTracker.Prelag.Service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Secure key generation
    private static final long EXPIRATION_DURATION = 86400000; // Token validity: 1 day in milliseconds

    /**
     * Generates a JWT token for the given email.
     *
     * @param email The email for which the token is generated.
     * @return The generated JWT token.
     */
    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date()) // Current time as issued date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DURATION)) // Token expiry
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // Sign with secure key
                .compact();
    }

    /**
     * Validates a JWT token against the provided email.
     *
     * @param token The JWT token to validate.
     * @param email The email to match against the token's subject.
     * @return True if the token is valid, false otherwise.
     */
    public static boolean validateToken(String token, String email) {
        try {
            Claims claims = parseToken(token); // Parse the token to get claims
            return claims.getSubject().equals(email) && !isTokenExpired(claims);
        } catch (JwtException | IllegalArgumentException e) {
            // Log exception for debugging purposes (e.g., invalid signature, expired token, etc.)
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Extracts the email from the token's payload (subject field).
     *
     * @param token The JWT token.
     * @return The email extracted from the token.
     */
    public static String extractEmailFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * Parses a JWT token and returns its claims.
     *
     * @param token The JWT token to parse.
     * @return Claims object containing the token's payload.
     */
    private static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if a token is expired based on its claims.
     *
     * @param claims The claims object from the token.
     * @return True if the token is expired, false otherwise.
     */
    private static boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
