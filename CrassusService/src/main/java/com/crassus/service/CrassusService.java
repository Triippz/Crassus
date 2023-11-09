package com.crassus.service;

import com.crassus.core.CrassusConstants;
import com.crassus.service.config.CrassusProperties;
import com.crassus.service.config.DefaultProfileUtil;
import com.crassus.service.config.logs.CRLFLogConverter;
import jakarta.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties({ CrassusProperties.class })
@ComponentScan(basePackages = { "com.crassus.*" })
@EntityScan(basePackages = { "com.crassus.models" })
public class CrassusService {

  private static final Logger log = LoggerFactory.getLogger(CrassusService.class);

  private final Environment env;

  public CrassusService(Environment env) {
    this.env = env;
  }

  /**
   * Initializes crassus.
   * <p>
   * Spring profiles can be configured with a program argument --spring.profiles.active=your-active-profile
   * <p>
   */
  @PostConstruct
  public void initApplication() {
    Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
    if (
      activeProfiles.contains(CrassusConstants.SPRING_PROFILE_DEVELOPMENT) &&
      activeProfiles.contains(CrassusConstants.SPRING_PROFILE_PRODUCTION)
    ) {
      log.error(
        "You have misconfigured your application! It should not run " +
        "with both the 'dev' and 'prod' profiles at the same time."
      );
    }
    if (
      activeProfiles.contains(CrassusConstants.SPRING_PROFILE_DEVELOPMENT) &&
      activeProfiles.contains(CrassusConstants.SPRING_PROFILE_CLOUD)
    ) {
      log.error(
        "You have misconfigured your application! It should not " +
        "run with both the 'dev' and 'cloud' profiles at the same time."
      );
    }
  }

  /**
   * Main method, used to run the application.
   *
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(CrassusService.class);
    DefaultProfileUtil.addDefaultProfile(app);
    Environment env = app.run(args).getEnvironment();
    logApplicationStartup(env);
  }

  private static void logApplicationStartup(Environment env) {
    String protocol = Optional
      .ofNullable(env.getProperty("server.ssl.key-store"))
      .map(key -> "https")
      .orElse("http");
    String applicationName = env.getProperty("spring.application.name");
    String serverPort = env.getProperty("server.port");
    String contextPath = Optional
      .ofNullable(env.getProperty("server.servlet.context-path"))
      .filter(StringUtils::isNotBlank)
      .orElse("/");
    String hostAddress = "localhost";
    try {
      hostAddress = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      log.warn("The host name could not be determined, using `localhost` as fallback");
    }
    log.info(
      CRLFLogConverter.CRLF_SAFE_MARKER,
      """

      ----------------------------------------------------------
      \tApplication '{}' is running! Access URLs:
      \tLocal: \t\t{}://localhost:{}{}
      \tExternal: \t{}://{}:{}{}
      \tProfile(s): \t{}
      ----------------------------------------------------------""",
      applicationName,
      protocol,
      serverPort,
      contextPath,
      protocol,
      hostAddress,
      serverPort,
      contextPath,
      env.getActiveProfiles().length == 0
        ? env.getDefaultProfiles()
        : env.getActiveProfiles()
    );
  }
}
