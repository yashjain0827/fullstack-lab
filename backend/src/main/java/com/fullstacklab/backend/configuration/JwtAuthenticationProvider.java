package com.fullstacklab.backend.configuration;

import com.fullstacklab.backend.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public class JwtAuthenticationProvider implements AuthenticationProvider {




    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    public JwtAuthenticationProvider(JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{



        String token = ((JwtAuthenticationToken) authentication).getToken();

        String username = jwtUtil.extractUsername(token);


        if(username == null) {
            throw new AuthenticationException("Invalid token") {};
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());

    }

}
