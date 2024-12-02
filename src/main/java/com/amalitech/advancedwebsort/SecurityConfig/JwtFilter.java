package com.amalitech.advancedwebsort.SecurityConfig;

import com.amalitech.advancedwebsort.Service.CustomUserDetailsService;
import com.amalitech.advancedwebsort.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private  final JwtService jwtService;
    private  final CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if( request.getServletPath().contains("api/v1/auth")){
            filterChain.doFilter(request,response);
            return;
        }

        String authHeader= request.getHeader("Authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer")) {

            filterChain.doFilter(request,response);
            return;
        }
        final String jwtToken= authHeader.substring(7);
           String userEmail = jwtService.extractUsername(jwtToken);
        Authentication securityContext= SecurityContextHolder.getContext().getAuthentication();
        if (userEmail!=null&& securityContext==null) {
            UserDetails userDetails= customUserDetailsService.loadUserByUsername(userEmail);
            UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null);
         authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
         SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request,response);
    }

}
