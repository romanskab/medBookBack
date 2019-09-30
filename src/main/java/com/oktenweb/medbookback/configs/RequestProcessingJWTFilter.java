package com.oktenweb.medbookback.configs;


import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RequestProcessingJWTFilter extends GenericFilterBean {

    // цей фільтр реагує на всі url
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // оголошуємо пустий об'єкт аутентифікації
        Authentication authentication = null;

        //  підганяємо запит під клас HttpServletRequest
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // дістаємо токен з header-а запиту
        String token = httpServletRequest.getHeader("Authorization");

        // якщо токен існує, то розшифровуємо його, і створюємо об'єкт аутентифікації authentication
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey("yes".getBytes())
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody()
                    .getSubject();
            System.out.println(user + "!!!!!!!!!!!---!!!!!");

            String[] array = user.split(" ");
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(array[1]));

            authentication = new UsernamePasswordAuthenticationToken(array[0], null, authorities);
        }

        // запихаємо об'єкт аутентифікації в контекст security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // передаємо ланцюг дій далі
        chain.doFilter(request, response);
    }
}
