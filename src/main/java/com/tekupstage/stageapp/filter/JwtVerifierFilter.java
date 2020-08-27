package com.tekupstage.stageapp.filter;

import com.google.common.base.Strings;
import com.tekupstage.stageapp.config.JwtConfig;
import com.tekupstage.stageapp.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtVerifierFilter extends OncePerRequestFilter {

    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private SecretKey secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String secret = "TEKUP_STAGE_APP_SECRET_TEKUP_STAGE_APP_SECRET";
        String authorization =  request.getHeader("Authorization");
        if(Strings.isNullOrEmpty(authorization) || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = authorization.replace("Bearer ","");
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            String username = claims.getBody().getSubject();
            var authorities = (List<Map<String,String>>) claims.getBody().get("authorities");
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(a -> new SimpleGrantedAuthority(a.get("authority")))
                    .collect(Collectors.toSet());
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (JwtException e){
            throw new IllegalStateException(String.format("Token %s can not be trusted",token));
        }
        filterChain.doFilter(request,response);
    }
}
