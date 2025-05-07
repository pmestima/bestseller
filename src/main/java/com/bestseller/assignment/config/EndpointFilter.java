package com.bestseller.assignment.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Profile("!test")
@Slf4j
@Order(1)
@Component
public class EndpointFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        long beginning = System.currentTimeMillis();

        String requestUri = ((RequestFacade) servletRequest).getRequestURI();
        String method = ((RequestFacade) servletRequest).getMethod();

        filterChain.doFilter(servletRequest, servletResponse);

        log.info("Request took {}ms: {} {}", System.currentTimeMillis() - beginning, method, requestUri);
    }
}
