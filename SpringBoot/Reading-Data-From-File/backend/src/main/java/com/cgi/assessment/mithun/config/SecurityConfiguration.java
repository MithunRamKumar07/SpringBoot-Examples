package com.cgi.assessment.mithun.config;

import com.cgi.assessment.mithun.security.SecurityFilter;
import com.cgi.assessment.mithun.util.AppConstants;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**<p> Security config class</p>
 *
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    @Profile("secure")
    public FilterRegistrationBean<Filter> secureFilterRegistration() {
        FilterRegistrationBean<Filter> secureRegistration = new FilterRegistrationBean<>();
        secureRegistration.setFilter(new SecurityFilter());
        secureRegistration.setName("securityFilter");
        secureRegistration.setOrder(AppConstants.NUMBER_ONE);
        return secureRegistration;
    }

}
