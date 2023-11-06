package com.crassus.service;

import com.crassus.service.config.AsyncSyncConfiguration;
import com.crassus.service.config.EmbeddedSQL;
import com.crassus.service.config.TestSecurityConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(
  classes = {
    CrassusService.class, AsyncSyncConfiguration.class, TestSecurityConfiguration.class,
  }
)
@EmbeddedSQL
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public @interface IntegrationTest {
}
