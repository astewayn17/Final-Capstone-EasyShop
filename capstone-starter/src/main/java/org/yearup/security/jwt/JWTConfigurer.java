package org.yearup.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    // Token provider used to create and validate JWTs
    private TokenProvider tokenProvider;

    // Constructor with a TokenProvider dependency
    public JWTConfigurer(TokenProvider tokenProvider) { this.tokenProvider = tokenProvider; }

    // Adds the custom JWTFilter to the Spring Security filter chain
    @Override
    public void configure(HttpSecurity http) {
        JWTFilter customFilter = new JWTFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}