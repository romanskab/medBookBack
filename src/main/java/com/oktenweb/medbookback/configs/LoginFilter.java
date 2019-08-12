package com.oktenweb.medbookback.configs;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.oktenweb.medbookback.entity.Patient;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        //this method react  on /login url  and retrive body from request
        //then map it to model AccountCredential
        Patient patient = new ObjectMapper()
                .readValue(httpServletRequest.getInputStream(), Patient.class);
        System.out.println(patient);
        // then  get default method getAuthenticationManager()
        // and set Authentication object based on data from creds object

        // if auth process if success we jump to line 65 successfulAuthentication()
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        patient.getUsername(),
                        patient.getPassword(),
                        Collections.emptyList()
                )
        );


    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        // if in prev method we was authenticate
        // we create token

        String jwtoken = Jwts.builder()
                .setSubject(auth.getName())
                .signWith(SignatureAlgorithm.HS512, "yes".getBytes())
//                .setExpiration(new Date(System.currentTimeMillis() + 200000))
                .compact();
        //and add it to header
        res.addHeader("Authorization", "Bearer " + jwtoken);

    }
}
