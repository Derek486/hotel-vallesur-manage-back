package com.miempresa.hotel.config;

import static com.miempresa.hotel.modelo.Role.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miempresa.hotel.schemas.ErrorResponse;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .disable())
                    .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("api/v1/auth/**").permitAll()
                        .requestMatchers("api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                        .requestMatchers("api/v1/contratoalquileres/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                        .requestMatchers("api/v1/departamentos/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                        .requestMatchers("api/v1/inquilinos/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                        .requestMatchers("api/v1/pagoalquiler/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                        .requestMatchers("api/v1/admin/**").hasRole(ADMIN.name())
                        .requestMatchers("api/v1/contratoalquileres/**").hasRole(ADMIN.name())
                        .requestMatchers("api/v1/departamentos/**").hasRole(ADMIN.name())
                        .requestMatchers("api/v1/inquilinos/**").hasRole(ADMIN.name())
                        .requestMatchers("api/v1/pagoalquiler/**").hasRole(ADMIN.name())
                        .anyRequest()
                        .authenticated())
                    .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint())
                    );
        return http.build();
    }
    private AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            ErrorResponse errorResponse = new ErrorResponse("Acceso denegado: " + accessDeniedException.getMessage());
            String jsonResponse = new ObjectMapper().writeValueAsString(errorResponse);

            response.getWriter().write(jsonResponse);
        };
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }
}
