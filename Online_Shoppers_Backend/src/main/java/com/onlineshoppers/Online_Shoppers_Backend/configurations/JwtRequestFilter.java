package com.onlineshoppers.Online_Shoppers_Backend.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.onlineshoppers.Online_Shoppers_Backend.service.JwtService;
import com.onlineshoppers.Online_Shoppers_Backend.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    private String userName;
        Claims claims;
    // private String userName;
    String token;
    public static String CURRENT_USER= "";


    // @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

        if (httpServletRequest.getServletPath().matches("/user/signup|/authenticate")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            String authorizationHeader = httpServletRequest.getHeader("Authorization");
            // String token =null;

            // String userName;
            token = authorizationHeader.substring(7);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                userName = jwtUtil.extractUsername(token);
                CURRENT_USER=userName; 
                claims = jwtUtil.extractAllClaims(token);
            }

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = jwtService.loadUserByUsername(userName);
                if (jwtUtil.validateToken(token, userDetails)) {
                    // if(jwtUtil.isTokenValid(token,userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }





    // @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

    //     final String requestTokenHeader = request.getHeader("Authorization");

    //     String username = null;
    //     String jwtToken = null;

    //     if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
    //         jwtToken = requestTokenHeader.substring(7);
    //         try {
            
    //             username = jwtUtil.extractUsername(jwtToken);
    //         } catch (IllegalArgumentException e) {
    //             System.out.println("Unable to get JWT Token");
    //         } catch (ExpiredJwtException e) {
    //             System.out.println("JWT Token has expired");
    //         }
    //     } else {
    //         System.out.println("JWT token does not start with Bearer");
    //     }

    //     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

    //         UserDetails userDetails = jwtService.loadUserByUsername(username);

    //         if (jwtUtil.validateToken(jwtToken, userDetails)) {

    //             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    //             usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    //             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    //         }
    //     }
    //     filterChain.doFilter(request, response);

    // }
}
