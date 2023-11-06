package com.crassus.errors.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public class ErrorHeaderUtil {

  private static final Logger log = LoggerFactory.getLogger(ErrorHeaderUtil.class);

  private ErrorHeaderUtil() {}

  public static HttpHeaders createFailureAlert(
    String applicationName,
    boolean enableTranslation,
    String entityName,
    String errorKey,
    String defaultMessage
  ) {
    log.error("Entity processing failed, {}", defaultMessage);

    String message = enableTranslation ? "error." + errorKey : defaultMessage;

    HttpHeaders headers = new HttpHeaders();
    headers.add("X-" + applicationName + "-error", message);
    headers.add("X-" + applicationName + "-params", entityName);
    return headers;
  }
}
