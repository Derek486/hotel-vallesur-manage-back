package com.example.demo.config;

import static com.example.demo.user.Permission.ADMIN_CREATE;
import static com.example.demo.user.Permission.ADMIN_DELETE;
import static com.example.demo.user.Permission.ADMIN_READ;
import static com.example.demo.user.Permission.ADMIN_UPDATE;
import static com.example.demo.user.Permission.MANAGER_CREATE;
import static com.example.demo.user.Permission.MANAGER_DELETE;
import static com.example.demo.user.Permission.MANAGER_READ;
import static com.example.demo.user.Permission.MANAGER_UPDATE;
import static com.example.demo.user.Role.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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


                        .requestMatchers(GET,"api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                        .requestMatchers(POST,"api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                        .requestMatchers(PUT,"api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                        .requestMatchers(DELETE,"api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                        
                        .requestMatchers("api/v1/admin/**").hasRole(ADMIN.name())

                        .requestMatchers(GET,"api/v1/admin/**").hasAuthority(ADMIN_READ.name())
                        .requestMatchers(POST,"api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT,"api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE,"api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())
                        
                        .anyRequest().authenticated())

                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
