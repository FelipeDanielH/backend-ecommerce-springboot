package com.ecomarket.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ecomarket.model.Usuario;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

     public String generateTokenWithUserDetails(Usuario usuario) {
        // Crear el token usando Jwts.builder()
        return Jwts.builder()
                .subject(usuario.getEmail()) // El "subject" sigue siendo el email del usuario
                .claim("id", usuario.getId()) // ID del usuario
                .claim("nombre", usuario.getNombre()) // Nombre del usuario
                .claim("email", usuario.getEmail()) // Correo electrónico
                .claim("telefono", usuario.getTelefono()) // Teléfono
                .claim("direccion", usuario.getDireccion()) // Dirección
                .claim("tipo", usuario.getTipo().name()) // Tipo de usuario (ej. ADMIN, USER)
                .claim("numeroCuenta", usuario.getNumeroCuenta())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), Jwts.SIG.HS512) // Firma usando el algoritmo HS512
                .compact(); // Crear el token JWT
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), Jwts.SIG.HS512) // Nota: Se usa Jwts.SIG.HS512, no SignatureAlgorithm.HS512
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }


    public String getRoleFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("tipo", String.class); // Extract the "tipo" claim
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token); // <- si falla, lanza excepción
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
