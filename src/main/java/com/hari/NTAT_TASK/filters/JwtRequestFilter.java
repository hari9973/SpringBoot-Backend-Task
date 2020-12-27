package com.hari.NTAT_TASK.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hari.NTAT_TASK.models.CustomErrorResponse;
import com.hari.NTAT_TASK.services.MyUserDetailsService;
import com.hari.NTAT_TASK.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String authorizationHeader = request.getHeader("Authorization");
            String username = null;
            String jwt = null;
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(jwt);
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request,response);
        }catch (SignatureException e){
            ObjectMapper mapper = new ObjectMapper();
            response.setStatus(401);
            response.getWriter().write(mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(new CustomErrorResponse(HttpStatus.UNAUTHORIZED,"invalid jwt token")));
        }
        catch (ExpiredJwtException e){
            ObjectMapper mapper = new ObjectMapper();
            response.setStatus(401);
            response.getWriter().write(mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(new CustomErrorResponse(HttpStatus.UNAUTHORIZED,"Jwt Token Expired")));
        }
    }
}
