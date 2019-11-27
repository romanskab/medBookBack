package com.oktenweb.medbookback.configs;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.oktenweb.medbookback.entity.User;
import com.oktenweb.medbookback.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private UserService userDetailsService;

    //конструктор нашого фільтра
    public LoginFilter(String url, AuthenticationManager authManager, UserService userDetailsService) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        //підганяємо наш запит під клас юзера
        System.out.println("attemptAuthentication:");
        User user = new ObjectMapper()
                .readValue(httpServletRequest.getInputStream(), User.class);
        System.out.println(user);

        //аутентифіковуємось, і повертаємо об'єкт аутентифікації
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        //після успішного виконання попереднього методу, створюємо токен і додаємо його в header відповіді
        UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getName());
        String jwt_user_token = userDetails.getUsername() + " ";
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            jwt_user_token += authority.getAuthority();
        }
        System.out.println(jwt_user_token);

        String jwtoken = Jwts.builder()
                .setSubject(jwt_user_token)
                .signWith(SignatureAlgorithm.HS512, "yes".getBytes())
//                .setExpiration(new Date(System.currentTimeMillis() + 200000))
                .compact();
        res.addHeader("Authorization", "Bearer " + jwtoken);

    }
}
