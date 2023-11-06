package com.crassus.errors.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

class ErrorHeaderUtilTest {

  @Test
  void createFailureAlertWithTranslation() {
    HttpHeaders headers = ErrorHeaderUtil.createFailureAlert(
      "myApp",
      true,
      "User",
      "404",
      "Failed to find user"
    );
    assertThat(headers.getFirst("X-myApp-error")).isEqualTo("error.404");
    assertThat(headers.getFirst("X-myApp-params")).isEqualTo("User");
  }

  @Test
  void createFailureAlertNoTranslation() {
    HttpHeaders headers = ErrorHeaderUtil.createFailureAlert(
      "myApp",
      false,
      "User",
      "404",
      "Failed to find user"
    );
    assertThat(headers.getFirst("X-myApp-error")).isEqualTo("Failed to find user");
    assertThat(headers.getFirst("X-myApp-params")).isEqualTo("User");
  }
}
