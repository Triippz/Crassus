package com.crassus.errors.rest;

import java.net.URI;

public final class ErrorConstants {

  public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
  public static final String ERR_VALIDATION = "error.validation";
  public static final String PROBLEM_BASE_URL = "https://www.crassuscommerce.com/problem";
  public static final URI DEFAULT_TYPE = URI.create(
    PROBLEM_BASE_URL + "/problem-with-message"
  );
  public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(
    PROBLEM_BASE_URL + "/constraint-violation"
  );

  public static final URI API_FAILURE = URI.create(PROBLEM_BASE_URL + "/api-failure");

  private ErrorConstants() {}
}
