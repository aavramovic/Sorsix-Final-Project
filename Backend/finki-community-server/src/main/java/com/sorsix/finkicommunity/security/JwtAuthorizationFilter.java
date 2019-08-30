package com.sorsix.finkicommunity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request){
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");

        if(token != null){
            String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
            if(username != null){
                User user = userRepository.findByUsername(username).get(); // Hm
                UserPrincipal principal = new UserPrincipal(user);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, principal.getAuthorities());
                return auth;
            }
            return null;
        }
        return null;
    }
}
