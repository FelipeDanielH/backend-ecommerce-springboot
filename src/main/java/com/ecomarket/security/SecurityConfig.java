package com.ecomarket.security;

import com.ecomarket.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {



    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilitar CSRF (solo en desarrollo)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // Permitir acceso a /auth
                        .requestMatchers("/productos/**").permitAll()// Permitir acceso a /productos
                        .requestMatchers("/carrito/**").permitAll() // Permitir acceso a /carritos
                        .requestMatchers("/usuarios/**").permitAll() // Permitir acceso a /usuarios
                        .requestMatchers("/categorias/**").permitAll() // Permitir acceso a /categorias
//                        .requestMatchers("/usuarios/**").permitAll() // Permitir acceso a /usuarios
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class); // Agregar el filtro JWT antes del filtro de autenticación por defecto
        
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configurar CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173");  // Permitir solicitudes desde el frontend (React)
        configuration.addAllowedMethod("GET");  // Métodos permitidos
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedMethod("OPTIONS");  // Incluir el método OPTIONS (para solicitudes preflight)
        configuration.addAllowedHeader("Authorization");  // Permitir la cabecera Authorization
        configuration.addAllowedHeader("Content-Type");  // Permitir la cabecera Content-Type
        configuration.setAllowCredentials(true);  // Permitir credenciales (cookies, autenticación)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Aplicar configuración a todas las rutas
        return source;
    }

    @Bean
    public CorsFilter corsFilter(CorsConfigurationSource corsConfigurationSource) {
        return new CorsFilter(corsConfigurationSource);
    }
}