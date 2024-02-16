package com.cgi.assessment.mithun.security;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
/** <p> Test for {@link SecurityFilter }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class SecurityFilterTest {

    @InjectMocks
    SecurityFilter securityFilter;

    MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
    MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
    MockFilterChain mockFilterChain = new MockFilterChain();

    @Test
    void testDoFilter() throws ServletException, IOException {
        mockHttpServletRequest.setServletPath("assessment/recipes/getByIngredients");
        mockHttpServletRequest.setMethod("GET");
        securityFilter.doFilter(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain);
    }
}
