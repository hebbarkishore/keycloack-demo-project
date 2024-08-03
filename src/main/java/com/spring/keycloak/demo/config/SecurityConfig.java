package com.spring.keycloak.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";

    private final JwtConverter jwtConverter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers("/h2-console/**").permitAll() // Allow access to H2 console
                // .requestMatchers(HttpMethod.GET, "/api/hello").permitAll()
                //.requestMatchers(HttpMethod.GET, "/employee/getAll/**").hasRole(ADMIN)
                //.requestMatchers(HttpMethod.GET, "/employee/id/**").hasAnyRole(ADMIN,USER)
                .anyRequest().authenticated() // Secure all other endpoints
        )
        .csrf((csrf) -> csrf
                .ignoringRequestMatchers("/h2-console/**") // Disable CSRF for H2 console
        );

        http.sessionManagement(sess -> sess.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));

        return http.build();
    }

}