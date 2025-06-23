package org.yearup.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * found. JWT = JSON web token
 */
public class JWTFilter extends GenericFilterBean {

    // Logger for debugging and tracking authentication events
    private static final Logger LOG = LoggerFactory.getLogger(JWTFilter.class);

    // The HTTP header used to transmit the JWT
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // Token provider responsible for validating and parsing JWTs
    private TokenProvider tokenProvider;

    // Constructs a JWTFilter with the given token provider
    public JWTFilter(TokenProvider tokenProvider) { this.tokenProvider = tokenProvider; }

    // Intercepts each HTTP request to check for a JWT token in the Authorization header
    // If a valid token is found, it sets the authentication in the security context
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LOG.debug("set Authentication to custom security context for '{}', uri: {}", authentication.getName(), requestURI);
        } else {
            LOG.debug("no valid JWT token found, uri: {}", requestURI);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // Extracts the JWT token from the Authorization header of the request
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}