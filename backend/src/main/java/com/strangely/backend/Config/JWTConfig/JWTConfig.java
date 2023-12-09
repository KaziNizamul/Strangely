package com.strangely.backend.Config.JWTConfig;

import com.strangely.backend.Config.AuthConfig.AuthProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTConfig extends OncePerRequestFilter {

    private final AuthProvider AP;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain fc) throws ServletException, IOException
    {
        String h = req.getHeader(HttpHeaders.AUTHORIZATION);
        if(h != null)
        {
            String[] he = h.split(" ");
            if (he.length == 2 && "Bearer".equals(he[0]))
            {
                try
                {
                    if ("GET".equals(req.getMethod()))
                    {
                        SecurityContextHolder.getContext().setAuthentication(AP.validateJWT(he[1]));
                    }
                    else
                    {
                        SecurityContextHolder.getContext().setAuthentication(AP.validateJWTForce(he[1]));
                    }
                } catch (RuntimeException e)
                {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }
        fc.doFilter(req, res);
    }
}
