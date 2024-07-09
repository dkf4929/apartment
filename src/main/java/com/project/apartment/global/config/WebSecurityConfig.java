package com.project.apartment.global.config;

import com.project.apartment.global.enums.AllPermitPath;
import com.project.apartment.global.enums.MemberPermitPath;
import com.project.apartment.global.jwt.JwtAuthenticationFilter;
import com.project.apartment.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .cors(c -> c.disable())
                .formLogin(f -> f.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(c -> c.frameOptions(f -> f.disable()).disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.GET, AllPermitPath.toArrayPath(HttpMethod.GET)).permitAll();
                    auth.requestMatchers(HttpMethod.POST, AllPermitPath.toArrayPath(HttpMethod.POST)).permitAll();
                    auth.requestMatchers(HttpMethod.GET, MemberPermitPath.toArrayPath(HttpMethod.GET)).hasRole("USER");
                    auth.requestMatchers(HttpMethod.POST, MemberPermitPath.toArrayPath(HttpMethod.POST)).hasRole("USER");
                    auth.anyRequest().permitAll();
                })
                .logout(logout -> logout.disable())
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }
}
