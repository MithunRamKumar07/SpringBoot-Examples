package com.cgi.assessment.mithun.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

/**<p> Filter class that performs security related checks </p>
 *
 */
@Slf4j
@Profile("secure")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Security Logic can be implemented based on the requirement
        //Just added the log statement for testing purpose
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        log.info("This log is added to see if filter is working fine");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
