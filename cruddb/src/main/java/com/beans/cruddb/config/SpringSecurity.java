package com.beans.cruddb.config;

import com.beans.cruddb.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity class configures authentication and authorization for the application.
 * It includes JWT filtering, password encoding, and security rules for different API endpoints.
 */

@Configuration
public class SpringSecurity {

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Configures security settings, including access control and JWT authentication.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request
                .requestMatchers("/api/**",
                        "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**",
                        "/redis/api/test/save", "api/scrapper/**"
                ).permitAll() // Allow access to Swagger & public APIs
                .requestMatchers("/users/**").authenticated() // Require authentication for user-related endpoints
                .requestMatchers("/admin/**").hasAuthority("ADMIN") // Only users with "ADMIN" role can access "/admin/**"
                .anyRequest().authenticated()) // Require authentication for all other endpoints
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before authentication filter
                .build();
    }

    /**
     * Provides a password encoder using BCrypt hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures authentication manager to handle user authentication.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception{
        return auth.getAuthenticationManager();
    }
}
