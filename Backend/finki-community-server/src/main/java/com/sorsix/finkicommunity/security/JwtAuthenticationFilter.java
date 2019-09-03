package com.sorsix.finkicommunity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sorsix.finkicommunity.domain.enums.Role;
import com.sorsix.finkicommunity.domain.requests.LoginViewModel;
import com.sorsix.finkicommunity.domain.responses.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginViewModel credentials = null;

        try{
            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
        }catch(IOException e){
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                new ArrayList<>()
        );



        Authentication auth = authenticationManager.authenticate(authenticationToken);

        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

        long expires = System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME;
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(expires))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();

        UserResponse userResponse = new UserResponse();
        userResponse.expiresIn = expires;
        userResponse.idToken = token;
        GrantedAuthority[] grantedAuthorities;
        userResponse.role = Role.valueOf(principal.getAuthorities()
                .stream()
                .findFirst()
                .map(
                        authority ->  authority.getAuthority().replace("ROLE_", "")
                )
                .orElse("USER")
        );
        userResponse.errorMessage = "";
        userResponse.valid = true;
        userResponse.username = principal.getUsername();

        String userResponseJsonString = new Gson().toJson(userResponse);

        out.print(userResponseJsonString);
        out.flush();
    }
}
