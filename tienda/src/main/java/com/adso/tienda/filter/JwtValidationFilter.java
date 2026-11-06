package com.adso.tienda.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adso.tienda.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component 
@RequiredArgsConstructor 
public class JwtValidationFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;

    @Override 
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException{
        String authHeder = request.getHeader("Authorization");

        if(authHeder == null || !authHeder.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error:\"Header Authorization is missing in the request\"}");
            return ;
        }

        String token = authHeder.replace("Bearer ", "");

        try {
            if (jwtService.istokenValid(token)) {
                String username = jwtService.extractUsername(token);
                Long userid = jwtService.extractUserId(token);
                Long rolid = jwtService.ectractRolId(token);

                request.setAttribute("username", username);
                request.setAttribute("userId", userid);
                request.setAttribute("rolId", rolid);

                filterChain.doFilter(request, response);
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Token is invalid or expired\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Validation faild\"}");
        }
    }

    @Override 
    protected boolean shouldNotFilter(HttpServletRequest request){
        String path = request.getRequestURI();
        String method = request.getMethod();

        boolean routProducts = "GET".equalsIgnoreCase(method) && path.startsWith("/api/v1/tienda/products") && !path.contains("/mine");

        return path.startsWith("/api/v1/tienda/auth") || routProducts;
    }
}
