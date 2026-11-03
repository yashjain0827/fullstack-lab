package com.fullstacklab.backend.configuration;

import com.fullstacklab.backend.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtValidationFilter  extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token = extractJwtFromEequest(request);
    if(token != null){
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(token);
        Authentication authresult = authenticationManager.authenticate(jwtAuthenticationToken);

        if(authresult.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authresult);
        }
    }


        filterChain.doFilter(request, response);

    }



    private  String extractJwtFromEequest(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;

    }
}
