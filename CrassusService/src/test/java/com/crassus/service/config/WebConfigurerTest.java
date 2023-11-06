package com.crassus.service.config;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletRegistration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Unit tests for the {@link WebConfigurer} class.
 */
class WebConfigurerTest {

  private WebConfigurer webConfigurer;

  private MockServletContext servletContext;

  private MockEnvironment env;

  private CrassusProperties props;

  @BeforeEach
  public void setup() {
    servletContext = spy(new MockServletContext());
    doReturn(mock(FilterRegistration.Dynamic.class))
      .when(servletContext)
      .addFilter(anyString(), any(Filter.class));
    doReturn(mock(ServletRegistration.Dynamic.class))
      .when(servletContext)
      .addServlet(anyString(), any(Servlet.class));

    env = new MockEnvironment();
    props = new CrassusProperties();

    webConfigurer = new WebConfigurer(env, props);
  }

  @Test
  void shouldCorsFilterOnApiPath() throws Exception {
    props.getCors().setAllowedOrigins(Collections.singletonList("other.domain.com"));
    props.getCors().setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    props.getCors().setAllowedHeaders(Collections.singletonList("*"));
    props.getCors().setMaxAge(1800L);
    props.getCors().setAllowCredentials(true);

    MockMvc mockMvc = MockMvcBuilders
      .standaloneSetup(new WebConfigurerTestController())
      .addFilters(webConfigurer.corsFilter())
      .build();

    mockMvc
      .perform(
        options("/test-cors")
          .header(HttpHeaders.ORIGIN, "other.domain.com")
          .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "POST")
      )
      .andExpect(status().isOk())
      .andExpect(
        header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "other.domain.com")
      )
      .andExpect(header().string(HttpHeaders.VARY, "Origin"))
      .andExpect(
        header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,PUT,DELETE")
      )
      .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true"))
      .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "1800"));

    mockMvc
      .perform(get("/test-cors").header(HttpHeaders.ORIGIN, "other.domain.com"))
      .andExpect(status().isOk())
      .andExpect(
        header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "other.domain.com")
      );
  }

  @Test
  void shouldCorsFilterOnOtherPath() throws Exception {
    // Setting up CORS configurations
    props.getCors().setAllowedOriginPatterns(Collections.singletonList("*"));
    props.getCors().setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    props.getCors().setAllowedHeaders(Collections.singletonList("*"));
    props.getCors().setMaxAge(1800L);
    props.getCors().setAllowCredentials(true);

    // Building the mock MVC instance
    MockMvc mockMvc = MockMvcBuilders
      .standaloneSetup(new WebConfigurerTestController())
      .addFilters(webConfigurer.corsFilter())
      .build();

    // Performing the mock request and verifying the response
    mockMvc
      .perform(get("/test/test-cors").header(HttpHeaders.ORIGIN, "other.domain.com"))
      .andExpect(status().isOk())
      .andExpect(
        header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "other.domain.com")
      ) // Expecting the 'Access-Control-Allow-Origin' header with the correct value
      .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true")); // Additionally, checking for the 'Access-Control-Allow-Credentials' header
  }

  @Test
  void shouldCorsFilterDeactivatedForNullAllowedOrigins() throws Exception {
    props.getCors().setAllowedOrigins(null);

    MockMvc mockMvc = MockMvcBuilders
      .standaloneSetup(new WebConfigurerTestController())
      .addFilters(webConfigurer.corsFilter())
      .build();

    mockMvc
      .perform(get("/test-cors").header(HttpHeaders.ORIGIN, "other.domain.com"))
      .andExpect(status().isOk())
      .andExpect(header().doesNotExist(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
  }

  @Test
  void shouldCorsFilterDeactivatedForEmptyAllowedOrigins() throws Exception {
    props.getCors().setAllowedOrigins(new ArrayList<>());

    MockMvc mockMvc = MockMvcBuilders
      .standaloneSetup(new WebConfigurerTestController())
      .addFilters(webConfigurer.corsFilter())
      .build();

    mockMvc
      .perform(get("/test-cors").header(HttpHeaders.ORIGIN, "other.domain.com"))
      .andExpect(status().isOk())
      .andExpect(header().doesNotExist(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
  }
}
