package com.crassus.service.config.apidoc;

import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>OpenApiEndpointConfiguration class.</p>
 */
@Configuration
@ConditionalOnClass(SpringDocConfigProperties.class)
@AutoConfigureAfter(CrassusSpringDocAutoConfiguration.class)
public class CrassusOpenApiEndpointConfiguration {

  /**
   * <p>crassusOpenApiEndpoint.</p>
   *
   * @param springDocConfigProperties a {@link SpringDocConfigProperties} object.
   * @return a {@link CrassusOpenApiEndpoint} object.
   */
  @Bean
  @ConditionalOnBean({ SpringDocConfigProperties.class })
  @ConditionalOnMissingBean
  @ConditionalOnAvailableEndpoint
  public CrassusOpenApiEndpoint crassusOpenApiEndpoint(
    SpringDocConfigProperties springDocConfigProperties,
    @Value("${spring.application.name:application}") String appName
  ) {
    return new CrassusOpenApiEndpoint(springDocConfigProperties, appName);
  }
}
