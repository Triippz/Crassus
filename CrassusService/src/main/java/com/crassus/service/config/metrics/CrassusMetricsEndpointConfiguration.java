package com.crassus.service.config.metrics;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsEndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>CrassusMetricsEndpointConfiguration class.</p>
 */
@Configuration
@ConditionalOnClass(Timed.class)
@AutoConfigureAfter(MetricsEndpointAutoConfiguration.class)
public class CrassusMetricsEndpointConfiguration {

  /**
   * <p>crassusMetricsEndpoint.</p>
   *
   * @param meterRegistry a {@link MeterRegistry} object.
   * @return a {@link CrassusMetricsEndpoint} object.
   */
  @Bean
  @ConditionalOnBean({ MeterRegistry.class })
  @ConditionalOnMissingBean
  @ConditionalOnAvailableEndpoint
  public CrassusMetricsEndpoint crassusMetricsEndpoint(MeterRegistry meterRegistry) {
    return new CrassusMetricsEndpoint(meterRegistry);
  }
}
